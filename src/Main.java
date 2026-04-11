package src;
import src.action.BasicAttack;
import src.engine.BattleEngine;
import src.engine.GameSetup;
import src.engine.SpeedBasedTurnOrder;
import src.engine.TurnOrderStrategy;
import src.level.LevelManager;
import src.ui.BattleDisplayUI;
import src.ui.ConsoleBattleDisplay;
import src.ui.ConsoleGameInput;
import src.ui.GameInputUI;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args){
        BattleDisplayUI display = new ConsoleBattleDisplay();
        GameInputUI input = new ConsoleGameInput();
        LevelManager levelManager = new LevelManager();
        TurnOrderStrategy strategy = new SpeedBasedTurnOrder();

        boolean keepPlaying = true;

        while(keepPlaying){
            //loading the setup screen
            GameSetup setup = new GameSetup(input, levelManager);
            setup.run();

            //starting the game
            BattleEngine engine = new BattleEngine(strategy, display, input);
            engine.startBattle(setup.getPlayer(), setup.getSpawnConfig());

            //post-game selection menu
            int choice = input.promptPostGameMenu();
            switch(choice){
                case 1 -> {
                    System.out.println("\n Replaying at " + setup.getDifficulty() + " difficulty...");
                    GameSetup replay = new GameSetup(input, levelManager);
                    replay.runReplay(setup.getDifficulty());
                    new BattleEngine(strategy, display, input).startBattle(replay.getPlayer(), replay.getSpawnConfig());
                    /*
                        after replay, the outer loop will prompt for post-game menu
                        again by falling through the next iteration
                     */
                }
                case 2 -> {/*fall through, outer loop runs full setup again*/}
                case 3 -> {
                keepPlaying = false;
                System.out.println("\n Thank you for playing! Bye Bye.\n");}
            }
        }
    }
}
