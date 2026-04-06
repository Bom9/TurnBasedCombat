package src.ui;
import java.util.list;
import item.Item;
import action.Action;
import entity.Combatant;
import entity.Player;
import level.DifficultyLevel;
import src.character.Combatant;

public interface GameInputUI {
    //Select a class, returns a newly constructed Player instance with class
    Player promptPlayerSelection();

    // Player picks 2 items, duplicates are allowed, retuurns list of exactly 2 chosen Item instances
    List<Item> promptItemSelection();

    //Choose Difficulty Level
    DifficultyLevel promptDifficultySelection();

    //Player turn options, - like a player pokemon battle menu
    Action promptActionChoice(Player player, List<Action> availableActions);

    //Player chooses enemies to attack
    Combatant promptTargetSelection(List<Combatant> enemies);

    //Player chooses item to use
    int promptItemSelection(Player player);

    //Post game options
    int promptPostGameMenu();

}
