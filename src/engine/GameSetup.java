package src.engine;

import src.character.Player;
import src.items.Item;
import src.level.*;
import src.ui.GameInputUI;

import java.util.List;
/*
    utilise/depend on GameInputUI and LevelManager to get the initial input from user
    to configure the necessary setting: player, items, difficulty level
    then return the configured settings
 */
public class GameSetup {
    private final GameInputUI inputUI;
    private final LevelManager levelManager;

    private Player chosenPlayer;
    private DifficultyLevel chosenDifficulty;
    private SpawnConfig spawnConfig;

    public GameSetup(GameInputUI inputUI, LevelManager levelManager){
        this.inputUI = inputUI;
        this.levelManager = levelManager;
    }

    /*
        loading the initial playing screen for user to select settings
     */
    public void run(){
        printHeader();

        chosenPlayer = inputUI.promptPlayerSelection();
        List<Item> items = inputUI.promptItemSelection();
        items.forEach(chosenPlayer::addItem);

        chosenDifficulty = inputUI.promptDifficultySelection();
        spawnConfig = levelManager.buildSpawnConfig(chosenDifficulty);

        System.out.println("\n Setup completed! Game starting.... Good Luck!");
    }

    /*
        resue the same setting if user chose to replay with the same setting
     */
    public void runReplay(DifficultyLevel difficulty){
        printHeader();
        System.out.println("You are replaying the difficulty "+ difficulty.getDisplayName());

        chosenPlayer = inputUI.promptPlayerSelection();
        List<Item> items = inputUI.promptItemSelection();
        items.forEach(chosenPlayer::addItem);

        chosenDifficulty = difficulty;
        spawnConfig = levelManager.buildSpawnConfig(chosenDifficulty);

        System.out.println("\n Setup completed! Game starting.... Good Luck!");
    }
    // getter methods to return
    public Player getPlayer(){
        return chosenPlayer;
    }
    public DifficultyLevel getDifficulty(){
        return chosenDifficulty;
    }
    public SpawnConfig getSpawnConfig(){
        return spawnConfig;
    }
    private void printHeader(){
        System.out.println("TURN-BASED COMBAT ARENA v1.0");
        System.out.println("    Immersive CLI battle    ");
    }
}
