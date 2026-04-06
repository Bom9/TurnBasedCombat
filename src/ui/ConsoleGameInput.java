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
        System.out.println("\n===================================================");
        System.out.println("              CHOOSE YOUR 2 ITEMS                  ");
        System.out.println("  (duplicates allowed)                             ");
        System.out.println("===================================================");
        System.out.println("  [1] Potion      – Heal 100 HP (capped at max HP)");
        System.out.println("  [2] Power Stone – Trigger Special Skill once for free");
        System.out.println("  [3] Smoke Bomb  – Enemy attacks deal 0 damage for 2 turns");
        System.out.println();

        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            int choice = readInt("Select item " + i + " (1-3): ", 1, 3);
            items.add(createItem(choice));
        }
        return items;
    }

    public DifficultyLevel promptDifficultySelection(){

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

    private Item createItem(int choice) {
        return switch (choice) {
            case 1 -> new Potion();
            case 2 -> new PoweStone();
            default -> new SmokeBomb();
        };
        }
    }


}
