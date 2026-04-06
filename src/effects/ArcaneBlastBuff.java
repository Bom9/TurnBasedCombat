package src.effects;

import src.character.Combatant;
import java.lang.reflect.Field;

public class ArcaneBlastBuff extends StatusEffect {
    private static final int ATTACK_BONUS = 10;

    public ArcaneBlastBuff(int durationTurns) {
        this.name = "Arcane Blast Buff";
        this.durationTurns = durationTurns;
        this.currentDuration = durationTurns;
    }

    @Override
    public void applyEffect(Combatant target) {
        try {
            Field attackField = Combatant.class.getDeclaredField("attack");
            attackField.setAccessible(true);

            int currentAttack = attackField.getInt(target);
            attackField.setInt(target, currentAttack + ATTACK_BONUS);

            System.out.println(target.getClass().getSimpleName()
                    + " gains +" + ATTACK_BONUS + " attack.");
        } catch (Exception e) {
            System.out.println("Failed to apply Arcane Blast Buff.");
        }
    }

    @Override
    public void removeEffect(Combatant target) {
        try {
            Field attackField = Combatant.class.getDeclaredField("attack");
            attackField.setAccessible(true);

            int currentAttack = attackField.getInt(target);
            attackField.setInt(target, currentAttack - ATTACK_BONUS);

            System.out.println(target.getClass().getSimpleName()
                    + "'s Arcane Blast Buff has worn off.");
        } catch (Exception e) {
            System.out.println("Failed to remove Arcane Blast Buff.");
        }
    }
}
