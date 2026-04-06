package src.effects;

import src.character.Combatant;

public class Stun extends StatusEffect {

    public Stun(int durationTurns) {
        this.name = "Stun";
        this.durationTurns = durationTurns;
        this.currentDuration = durationTurns;
    }

    @Override
    public void applyEffect(Combatant target) {
        System.out.println(target.getClass().getSimpleName() + " is stunned and cannot act!");
    }

    @Override
    public void removeEffect(Combatant target) {
        System.out.println(target.getClass().getSimpleName() + " is no longer stunned.");
    }
}
