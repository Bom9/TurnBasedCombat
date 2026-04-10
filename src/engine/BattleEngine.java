package src.engine;

import src.action.*;
import src.character.*;
import src.level.*;
import src.ui.*;

import java.util.List;
import java.util.ArrayList;

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
    }
    public void startGame() {}
    public void executeRound() {}
    public void processAction(Action action, Combatant user, List<Combatant> targets) {}
    public boolean checkWinCondition() { return false; }
    public boolean checkLossCondition() { return false; }
    public void handleBackupSpawns() {}
    public void endTurn() {}
}