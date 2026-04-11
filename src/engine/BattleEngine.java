package src.engine;

import src.action.*;
import src.character.*;
import src.level.*;
import src.ui.*;
import src.items.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/*
    the control class that manages the gaming logic, coordinating all other classes
 */
public class BattleEngine {

    private final TurnOrderStrategy turnOrderStrategy;
    private final BattleDisplayUI display;
    private final GameInputUI input;

    private Player player;
    private List<Enemy> activeEnemies;
    private List<Enemy> backupEnemies;
    private TurnOrderStrategy turnOrder;
    private boolean backupSpawned;
    private int round;

    private BasicAttack basicAttackAction = new BasicAttack();
    private Defend defendAction = new Defend();

    public BattleEngine(TurnOrderStrategy turnOrderStrategy,
                        BattleDisplayUI display,
                        GameInputUI input){
        this.turnOrderStrategy = turnOrderStrategy;
        this.display = display;
        this.input = input;
    }
    /*
        the main method to execute the game with the selected player and spawn
        calling other methods to make this main method short and clean
        return true or false to indicate play win or lose
     */
    public boolean startBattle(Player player, SpawnConfig spawnConfig){
        this.player = player;
        this.activeEnemies = new ArrayList<>(spawnConfig.getInitialSpawn());
        this.backupEnemies = new ArrayList<>(spawnConfig.getBackupSpawn());
        this.backupSpawned = false;
        this.round = 0;

        while(true){
            round++;
            display.displayRoundStart(round);

            // if enemies all eliminated, check backup spawn
            if(!backupSpawned && !backupEnemies.isEmpty() && activeEnemies.isEmpty()){
                activeEnemies.addAll(backupEnemies);
                backupEnemies.clear();
                backupSpawned = true;
                display.displayCombatLog("Surprise!\nBACKUP ENEMIES SPAWN! "+activeEnemies.size() +" new enemies enter the arena!");
                activeEnemies.forEach(e ->
                        display.displayCombatLog(" + "+e.getName()+"(HP: "+e.getHP()+"/"+e.getMaxHP()+")")
                );
            }

            //process each combatant's turn based on speed order, easily change to different turn order
            List<Combatant> allCombatants = buildAllCombatants();
            List<Combatant> turnOrder = turnOrderStrategy.sortCombatants(allCombatants);

            for(Combatant combatant: turnOrder){
                if(!combatant.isAlive()) continue;;

                display.displayTurnStart(combatant);

                //resolve existing effect, if combatant is stun then skip the turn
                if(combatant.processStun()){
                    display.displayCombatLog(combatant.getName() + " is Stunned - turn skipped!");
                    continue;
                }

                //combatant not stunned, then proceed to execute the turn
                if(combatant instanceof Player p){
                    executePlayerTurn(p);
                }else if(combatant instanceof Enemy e){
                    executeEnemyTurn(e);
                }
                //decrese non stun effect duration after combatants action
                combatant.decrementNonStunEffects();

                //during mid round, check if game has ended by looking at player.isAlive()
                if(checkGameOver())
                    return evaluateOutcome(); //return true (win) or false (lose)
            }

            //end of round display
            display.displayCombatantStatus(buildAllCombatants());
            if(checkGameOver())
                return evaluateOutcome();
        }
    }

    private boolean evaluateOutcome(){
        long remaining = activeEnemies.stream().filter(Enemy::isAlive).count()+ backupEnemies.size();
        if(!player.isAlive()){
            //display player defeated page
            display.displayDefeat((int) remaining, round);
            return false;
        }else{
            display.displayVictory(player.getName(), player.getAttack(), player.getHP(), player.getMaxHP(), round,
                    player.getInitialInventory(), player.getInventory());
            return true;
        }
    }

    private boolean checkGameOver(){
        if(!player.isAlive()) return true;
        boolean enemiesRemain = !activeEnemies.isEmpty() || !backupEnemies.isEmpty();
        return !enemiesRemain;
    }

    private void executeEnemyTurn(Enemy enemy){
        Action action = enemy.decideAndGetAction(List.of(player));

        boolean isProtected = player.isProtectedFromAttack();
        action.execute(enemy, List.of(player));

        if(isProtected){
            display.displayCombatLog(enemy.getName() + " attacks "+player.getName()
                + " - blocked by smoke Bomb! (0 damage)");
        }else{
            int damage = Math.max(0, enemy.getAttack() - player.getEffectiveDef());
            if(!player.isAlive()){
                display.displayCombatLog(enemy.getName() + " attacks " + player.getName()
                    +" for "+damage + " damage! "+ player.getName()+" is DEFEATED!");
            }else{
                display.displayCombatLog(enemy.getName() + " attacks " + player.getName()
                    + " for "+ damage + " damage! (HP: "+player.getHP()+"/"+player.getMaxHP()+")");
            }
        }
    }

    private void executePlayerTurn(Player player){
        /*
            handle the player turn process, including the monitoring of CD
            CD decrement before action choice so the skill become available on the correct turn
            eg. ShieldBash used in round 2 will have CD set to 3
            decrement on each turn, when at round 5, CD become 0, and is available for use
         */
        player.decrementSkillCooldown();

        List<Action> available = buildAvailableActions(player);
        Action chosen = input.promptActionChoice(player, available);

        if(chosen instanceof BasicAttack){
            Combatant target = input.promptTargetSelection(aliveEnemiesAsCombatants());
            int damage = Math.max(0, player.getAttack() - target.getEffectiveDef());
            chosen.execute(player, List.of(target));
            if(target.isAlive()){
                display.displayCombatLog(player.getName() + " attacks " + target.getName()
                + " for "+damage+" damage! (HP: " + target.getHP()+"/"+ target.getMaxHP()+")");
            }else{
                display.displayCombatLog(player.getName() + " attacks " + target.getName()
                        + " for "+damage+" damage! " + target.getName() + " is ELIMINATED!");
                removeDeadEnemies();
            }
        }else if(chosen instanceof Defend){
            chosen.execute(player, List.of());
            display.displayCombatLog(player.getName() + " takes a defensive stance (+10 DEFEND for 2 turns");
        }else if(chosen instanceof SpecialSkill skill){
            executePlayerSpecialSkill(player, skill);
            player.triggerSkillCooldown();
        }else if(chosen instanceof UseItem){
            executePlayerItem(player);
        }
    }

    private void executePlayerItem(Player player){
        int index = input.promptItemSelection(player);
        Item item = player.removeItem(index);

        if(item instanceof SmokeBomb){
            item.use(player, List.of());
            display.displayCombatLog(player.getName()+ " uses Smoke Bomb! Enemy attacks deal 0 damage for 2 turns.");
        }else if(item instanceof Potion){
            int hpBefore = player.getHP();
            item.use(player, List.of());
            display.displayCombatLog((player.getName() + " uses Potion! Healed "
                +(player.getHP() - hpBefore) + " HP (HP: "+player.getHP()+"/"+player.getMaxHP()+")"));
        }else if(item instanceof PowerStone){
            display.displayCombatLog(player.getName() + "activates Power Stone - triggering "
                + player.getSpecialSkill().getName()+"!");
            SpecialSkill skill = player.getSpecialSkill();
            if(skill.isAreaOfEffect()){
                item.use(player, aliveEnemiesAsCombatants());
                int attackAfter = player.getAttack();
                display.displayCombatLog("  Arcane Blast hits all enemies!");
                if(player instanceof Wizard w){
                    display.displayCombatLog("  Wizard Attack is now "+attackAfter+"!");
                }
                removeDeadEnemies();
            }else{
                Combatant target = input.promptTargetSelection(aliveEnemiesAsCombatants());
                item.use(player, List.of(target));
                display.displayCombatLog(" "+player.getSpecialSkill().getName()+" hits "+target.getName()+"!");
                if(target.isStunned()){
                    display.displayCombatLog("  "+target.getName()+" is STUNNED for 2 turns!");
                }
                if(!target.isAlive()){
                    display.displayCombatLog("  "+target.getName()+" is ELIMINATED!");
                    removeDeadEnemies();
                }
            }
        }
    }

    private void executePlayerSpecialSkill(Player player, SpecialSkill skill){
        //only two condition, targeting area or single target
        if(skill.isAreaOfEffect()){
            List<Combatant> targets = aliveEnemiesAsCombatants();
            int attackBefore = player.getAttack();
            skill.execute(player, targets);
            display.displayCombatLog(player.getName() + " unleashes "+ skill.getName() + " on All enemies!");
            for(Combatant c: targets){
                int damage = Math.max(0, attackBefore - c.getEffectiveDef());
                if(c.isAlive()){
                    display.displayCombatLog(c.getName()+ " takes "+damage
                    + " damage (HP: " + c.getHP()+"/"+c.getName()+" takes "+damage);
                }else{
                    display.displayCombatLog(c.getName()+ " takes "+damage
                            + " damage - ELIMINATED!");
                    removeDeadEnemies();
                }
            }
            if(player instanceof Wizard w && w.getAttack() > attackBefore){
                display.displayCombatLog("  Wizard Attack boosted to "+w.getAttack()+"!");
            }

        }else{
            //single target
            Combatant target = input.promptTargetSelection(aliveEnemiesAsCombatants());
            int damage = Math.max(0, player.getAttack() - target.getEffectiveDef());
            skill.execute(player, List.of(target));
            display.displayCombatLog(player.getName()+ " uses "+skill.getName()
            + " on "+target.getName() + " for "+ damage + " damage! ");
            if(target.isStunned()){
                display.displayCombatLog("  "+target.getName()+" is STUNNED for 2 turns!");
            }
            if(!target.isAlive()){
                display.displayCombatLog("  "+ target.getName()+" is ELIMINATED!");
                removeDeadEnemies();
            }
        }
    }

    private void removeDeadEnemies(){
        activeEnemies.removeIf(e-> ! e.isAlive());
    }
    private List<Combatant> aliveEnemiesAsCombatants(){
        //modern java stream API to filter out alive enemies
        return activeEnemies.stream().filter(Enemy::isAlive).collect(Collectors.toList());
    }
    private List<Action> buildAvailableActions(Player player){
        List<Action> actions = new ArrayList<>();
        actions.add(basicAttackAction);
        actions.add(defendAction);
        if(player.hasItems()){
            actions.add(new UseItem());
        }
        /*
            only add special skill as selectable option when it is ready
            when on cooldown it is shown as non-numbered display
            so the valid input range does not include it
         */
        if(player.isSkillReady()){
            actions.add(player.getSpecialSkill());
        }
        return actions;
    }


    private static class UseItem implements Action{
        @Override
        public void execute(src.character.Combatant c, List<src.character.Combatant> t){}
        @Override
        public String getName(){return "Use Item";}
        @Override
        public String getDescription(){
            return "Use a single-use item from your inventory. ";
        }
    }

    private List<Combatant> buildAllCombatants(){
        List<Combatant> all = new ArrayList<>();
        all.add(player);
        all.addAll(activeEnemies);
        return all;
    }

    public void startGame() {}
    public void executeRound() {}
    public void processAction(Action action, Combatant user, List<Combatant> targets) {}
    public boolean checkWinCondition() { return false; }
    public boolean checkLossCondition() { return false; }
    public void handleBackupSpawns() {}
    public void endTurn() {}
}