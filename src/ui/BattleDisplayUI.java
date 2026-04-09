package src.ui;

import java.util.List;
import src.character.Combatant;
import src.character.Player;

public interface BattleDisplayUI {
    //Print line to combat log//
    void displayCombatLog(String message);

    //Prints current status of every combatant at round
    void displayCombatantStatus(List<Combatant> combatants);

    //round number
    void displayRoundStart(int roundNumber);

    void displayTurnStart(Combatant combatant);

    void displayVictory(int playerHP, int maxHP, int totalRounds);

    void displayDefeat(int enemiesRemaining, int totalRounds);

    void displaySeparator();
}
