package src.effects;

import src.character.Combatant;

public abstract class StatusEffect {
    protected String name;
    protected int durationTurns;
    protected int currentDuration;

    public abstract void applyEffect(Combatant target);
    public abstract void removeEffect(Combatant target);
    
    public void decrementDuration() {}
    public boolean isExpired() { return false; }
    public String getName() { return null; }
    public int getDuration() { return 0; }
}