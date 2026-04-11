package src.engine;

import src.action.*;
import src.character.*;
import src.level.*;
import src.ui.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

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

    private BasicAttack basicAttack = new BasicAttack();
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

                }
            }

        }
    }

    private void executePlayerTurn(Player player){

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