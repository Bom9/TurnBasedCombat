package src.effects;

import src.character.Combatant;

public abstract class StatusEffect {
    protected String name;
    protected int durationTurns;
    protected int currentDuration;

    public abstract void applyEffect(Combatant target);
    public abstract void removeEffect(Combatant target);

    public void decrementDuration() {
        if (currentDuration > 0) {
            currentDuration--;
        }
    }

    public boolean isExpired() {
        return currentDuration <= 0;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return currentDuration;
    }
}