package src.effects;

import src.character.Combatant;
import java.lang.reflect.Field;

public class SmokeBombEffect extends StatusEffect {

    private static final int ATTACK_REDUCTION = 5;

    public SmokeBombEffect(int durationTurns) {
        this.name = "Smoke Bomb";
        this.durationTurns = durationTurns;
        this.currentDuration = durationTurns;
    }

    @Override
    public void applyEffect(Combatant target) {
        try {
            Field attackField = Combatant.class.getDeclaredField("attack");
            attackField.setAccessible(true);

            int currentAttack = attackField.getInt(target);
            attackField.setInt(target, currentAttack - ATTACK_REDUCTION);

            System.out.println(target.getClass().getSimpleName()
                    + "'s attack reduced by " + ATTACK_REDUCTION + "!");
        } catch (Exception e) {
            System.out.println("Failed to apply Smoke Bomb effect.");
        }
    }

    @Override
    public void removeEffect(Combatant target) {
        try {
            Field attackField = Combatant.class.getDeclaredField("attack");
            attackField.setAccessible(true);

            int currentAttack = attackField.getInt(target);
            attackField.setInt(target, currentAttack + ATTACK_REDUCTION);

            System.out.println(target.getClass().getSimpleName()
                    + "'s attack has returned to normal.");
        } catch (Exception e) {
            System.out.println("Failed to remove Smoke Bomb effect.");
        }
    }
}
