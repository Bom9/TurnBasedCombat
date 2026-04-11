package src.ui;
import src.action.*;
import src.character.*;
import src.items.*;
import src.level.DifficultyLevel;
import src.action.SpecialSkill;

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

    @Override
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

    @Override
    public DifficultyLevel promptDifficultySelection(){
        System.out.println("\n====================================================");
        System.out.println("              CHOOSE DIFFICULTY                    ");
        System.out.println("====================================================");
        System.out.println("  [1] Easy   – 3 Goblins");
        System.out.println("  [2] Medium – 1 Goblin + 1 Wolf | Backup: 2 Wolves");
        System.out.println("  [3] Hard   – 2 Goblins         | Backup: 1 Goblin + 2 Wolves");

        System.out.println("\n  Enemy Attributes:");
        System.out.printf("    Goblin │ HP:%-3d ATK:%-3d DEF:%-3d SPD:%-3d%n",
                Goblin.BASE_HP, Goblin.BASE_ATK, Goblin.BASE_DEF, Goblin.BASE_SPD);
        System.out.printf("    Wolf   │ HP:%-3d ATK:%-3d DEF:%-3d SPD:%-3d%n",
                Wolf.BASE_HP, Wolf.BASE_ATK, Wolf.BASE_DEF, Wolf.BASE_SPD);
        System.out.println();

        int choice = readInt("Enter choice (1-3): ", 1, 3);
        return switch (choice) {
            case 1  -> DifficultyLevel.EASY;
            case 2  -> DifficultyLevel.MEDIUM;
            default -> DifficultyLevel.HARD;
        };
    }

    public Action promptActionChoice(Player player, List<Action> availableActions) {
        System.out.println("\n  Choose your action:");

        for (int i = 0; i < availableActions.size(); i++){
            Action a = availableActions.get(i);
            String extra = (a instanceof SpecialSkill) ? " [READY]": "";
            System.out.printf("    [%d] %s%s%n", i, i+1, a.getName(), extra);
        }

        if (!player.isSkillReady()){
            System.out.printf("    [-] %s  (on cooldown - %d turn%s remaining)%n",
                    player.getSpecialSkill().getName(),
                    Player.getSkillCooldown(),
                    player.getSkillCooldown() == 1 ? "": "s");
        }

        int choice = readInt("  Enter choice (1-" availableActions.size() + "):",
                1, availableActions.size());
        return availableActions.get(choice-1);
    }

    @Override
    public Combatant promptTargetSelection(List<Combatant> enemies){
        System.out.println("\n  Select target:");
        for (int i = 0; i < enemies.size(); i++) {
            Combatant e = enemies.get(i);
            System.out.printf("    [%d] %s  HP: %d/%d%s%n",
                    i + 1,
                    e.getName(),
                    e.getHP(),
                    e.getMaxHP(),
                    e.isStunned() ? " [STUNNED]" : "");
        }
        int choice = readInt("  Enter choice (1-" + enemies.size() + "): ", 1, enemies.size());
        return enemies.get(choice - 1);
    }

//    public int promptItemSelection(Player player) {
//        List<item.item> inventory = player.getInventory();
//        System.out,println("\n Select item to use:");
//        for (int i = 0; i < inventory.size(); i++){
//            System.out.printf("    [%d] %s - %s%n", i+1, inventory.get(i).getName(), inventory.get(i).getDescription());
//        }
//        return readInt("  Enter choice (1-" + inventory.size() + "): ", 1, inventory.size())-1;
//    }

    @Override
    public int promptPostGameMenu() {
        System.out.println("  ─────────────────────────────────────────");
        System.out.println("    What would you like to do next?        ");
        System.out.println("    [1] Replay with the same settings      ");
        System.out.println("    [2] Start a new game                   ");
        System.out.println("    [3] Exit                               ");
        System.out.println("  ─────────────────────────────────────────");
        return readInt("  Enter choice (1-3): ", 1, 3);
    }
    }

    private int readInt(String prompt, int min, int max)
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
            case 2 -> new PowerStone();
            default -> new SmokeBomb();
        };
        }
    }


}
