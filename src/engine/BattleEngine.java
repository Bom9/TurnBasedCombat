package src.engine;

import src.actions.*;
import src.character.*;
import src.level.*;
import src.ui.*;
import src.items.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
/*
    the control class that manages the gaming logic, coordinating all other classes
 */
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