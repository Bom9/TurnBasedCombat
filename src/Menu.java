package src;
import java.util.List;
import java.util.Scanner;
public class Menu {

    private Scanner scanner;
    private CombatLogger combatLogger;

    public String promptClassSelection() { return null; }
    public int promptDifficultySelection() { return 0; }
    public Item promptItemSelection(Player player) { return null; }
    public Action promptActionChoice(Player player) { return null; }
    public void displayCombatLog(String message) {}
    public void displayCombatStatus(List<Combatant> players, List<Combatant> enemies) {}
    public void displayGameOver(boolean victory) {}
}