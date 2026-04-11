package src;
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
            GameSetup setup = new GameSetup(input, levelManager);
        }
    }
}
