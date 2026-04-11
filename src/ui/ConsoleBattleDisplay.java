package src.ui;
import src.character.Combatant;
import src.character.Player;
import src.items.Item;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ConsoleBattleDisplay implements BattleDisplayUI{
    private static final String SEPARATOR = "======================================================";

    @Override
    public void displayCombatLog(String message) {
        System.out.println("  >> " + message);
    }

    @Override
    public void displayCombatantStatus(List<Combatant> combatants) {
        System.out.println("\n  -------------------Combatant Status--------------------------");
        for (Combatant c : combatants) {
            String status;
            if (c.isAlive()){
                status = "[ALIVE] " + c.getStatusString();
            } else {
                status = "[ELIMINATED] " + c.getName();
            }
            System.out.println("    " + status);
        }
    }

    @Override
    public void displayRoundStart(int roundNumber) {
        System.out.println("\n" + SEPARATOR);
        System.out.printf("  ROUND %d%n", roundNumber);
        System.out.println(SEPARATOR);
    }

    @Override
    public void displayTurnStart(Combatant combatant) {

        System.out.printf("%n --%s's turn --%n", combatant.getName());
    }

    /*
        Victory screen to show the statistics such as how many rounds, live remaining, items and
     */
    @Override
    public void displayVictory(String playerName, int playerAttack, int playerHP, int maxHP, int totalRounds, List<Item> initialItems, List<Item> remainingItems) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("     VICTORY!  Congratulations, you defeated all enemies!   ");
        System.out.println(SEPARATOR);
//        System.out.printf("  Remaining HP : %d / %d%n", playerHP, maxHP);
//        System.out.printf("  Total Rounds : %d%n", totalRounds);
//        System.out.println(SEPARATOR + "\n");

        // printing the basic statistic for victory page
        StringBuilder stats = new StringBuilder();
        stats.append("Remaining HP: ").append(playerHP).append(" / ").append(maxHP);
        stats.append(" | Total Rounds: ").append(totalRounds);
        if(playerName.equals("Wizard"))
            stats.append(" | Final wizard Attack: ").append(playerAttack);

        /*
            following three for loop if to for printing the complicated tracked item usage
            first for loop to map remaining items
         */
        Map<String, Integer> initialCounts = new LinkedHashMap<>();
        for(Item item: remainingItems)
            initialCounts.merge(item.getName(), 1, Integer::sum);

        //count the available amount in inventory
        Map<String, Integer> remainingCounts = new LinkedHashMap<>();
        for(Item item: remainingItems)
            remainingCounts.merge(item.getName(), 1, Integer::sum);

        //append every originally chosen item type, remaining defaults to 0 if used
        for(String itemName: initialCounts.keySet()){
            int left = remainingCounts.getOrDefault(itemName, 0);
            stats.append(" | \nRemaining ").append(itemName).append(": ").append(left);
        }

        System.out.println(stats);
        System.out.println();
        System.out.println(SEPARATOR + "\n");

    }

    @Override
    public void displayDefeat(int enemiesRemaining, int totalRounds) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("     DEFEATED!  Don't give up, try again!");
        System.out.println(SEPARATOR);
        System.out.printf("  Enemies Remaining : %d%n", enemiesRemaining);
        System.out.printf("  Total Rounds Survived : %d%n", totalRounds);
        System.out.println(SEPARATOR + "\n");
    }

    @Override
    public void displaySeparator() {
        System.out.println("  ─────────────────────────────────────────────────────");
    }
}
