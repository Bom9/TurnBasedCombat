package src.ui;
import action.*;
import entity.*;
import item.*;
import level.DifficultyLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleGameInput {

    private final Scanner scanner;

    public ConsoleGameInput(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Player promptPlayerSelection() {
        System.out.println("\n====================================================");
        System.out.println("             CHOOSE YOUR CHARACTER CLASS              ");
        System.out.println("=====================================================\n");
        System.out.printf("  [1] Warrior  │ HP:%-4d ATK:%-3d DEF:%-3d SPD:%-3d │ Shield Bash%n",
                Warrior.BASE_HP, Warrior.BASE_ATK, Warrior.BASE_DEF, Warrior.BASE_SPD);
        System.out.printf("  [2] Wizard   │ HP:%-4d ATK:%-3d DEF:%-3d SPD:%-3d │ Arcane Blast%n",
                Wizard.BASE_HP, Wizard.BASE_ATK, Wizard.BASE_DEF, Wizard.BASE_SPD);
        System.out.println();

        int choice = readInt("Enter choice (1-2):", 1, 2);
        return (choice == 1) ? new Warrior() :  new Wizard();
    }

    public List<Item> promptItemSelection(){
        return Item;
    }

    public DifficultyLevel promptDifficultySelection(){
        return;
    }

    public Action promptActionChoice(Player player, List<Action> availableActions) {
        return;
    }

    public Combatant promptTargetSelection(List<Combatant> enemies){
        return enemies;
    }

    public int promptItemSelection(Player player) {
        return 0;
    }

    public int promptPostGameMenu() {
        return 0;
    }

    private int readInt(String prompt, int main, int max)
    {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
                System.out.printf("  Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid Input - please enter a number.");
            }
        }
    }



}
