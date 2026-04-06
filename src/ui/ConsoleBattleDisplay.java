package src.ui;
import entity.Combatant;
import entity.Player;
import java.util.List;


public class ConsoleBattleDisplay implements BattleDisplayUI{
    private static final String SEPARATOR = "═══════════════════════════════════════════════════════════";

    @Override
    public void displayCombatLog(String message) {
        System.out.println("  >> " + message);
    }

    @Override
    public void displayCombatantStatus(List<Combatant> combatants) {
        System.out.println("\n  ----------Combatant Status----------------------------------");
        for (Combatant c : combatants) {
            String status;
            if (c.isAlive){
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

    @Override
    public void displayVictory(int playerHP, int maxHP, int totalRounds) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("     VICTORY!  Congratulations, you defeated all enemies!   ");
        System.out.println(SEPARATOR);
        System.out.printf("  Remaining HP : %d / %d%n", playerHP, maxHP);
        System.out.printf("  Total Rounds : %d%n", totalRounds);
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
