package src.engine;

import src.actions.Action;
import src.character.Combatant;
import src.ui.Menu;

import java.util.List;
public class BattleEngine {

     private List<Combatant> players;
     private List<Combatant> enemies;
     private TurnOrderStrategy turnOrder;
     private LevelManager levelManager;
     private Menu menu;
     private int currentRound;
     private boolean gameOver;

    public void startGame() {}
    public void executeRound() {}
    public void processAction(Action action, Combatant user, List<Combatant> targets) {}
    public boolean checkWinCondition() { return false; }
    public boolean checkLossCondition() { return false; }
    public void handleBackupSpawns() {}
    public void endTurn() {}
}