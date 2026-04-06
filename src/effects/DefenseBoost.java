package src.effects;

import src.character.Combatant;
import java.lang.reflect.Field;

public class DefenseBoost extends StatusEffect {
    private static final int BOOST_AMOUNT = 10;

    public DefenseBoost(int durationTurns) {
        this.name = "Defense Boost";
        this.durationTurns = durationTurns;
        this.currentDuration = durationTurns;
    }

    @Override
    public void applyEffect(Combatant target) {
        try {
            Field defenseField = Combatant.class.getDeclaredField("defense");
            defenseField.setAccessible(true);

            int currentDefense = defenseField.getInt(target);
            defenseField.setInt(target, currentDefense + BOOST_AMOUNT);

            System.out.println(target.getClass().getSimpleName()
                    + " gains +" + BOOST_AMOUNT + " defense for "
                    + durationTurns + " turns.");
        } catch (Exception e) {
            System.out.println("Failed to apply Defense Boost.");
        }
    }

    @Override
    public void removeEffect(Combatant target) {
        try {
            Field defenseField = Combatant.class.getDeclaredField("defense");
            defenseField.setAccessible(true);

            int currentDefense = defenseField.getInt(target);
            defenseField.setInt(target, currentDefense - BOOST_AMOUNT);

            System.out.println(target.getClass().getSimpleName()
                    + "'s Defense Boost has worn off.");
        } catch (Exception e) {
            System.out.println("Failed to remove Defense Boost.");
        }
    }
}