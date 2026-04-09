package src.effects;

import src.character.Combatant;

public abstract class StatusEffect {

    protected final String name;
    protected int duration;

    protected StatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        if (duration > 0) {
            duration--;
        }
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    
    public void onApply(Combatant target) {}

    
    public void onExpire(Combatant target) {}

    
    public boolean isTurnBlocking() {
        return false;
    }

    
    public boolean nullifiesEnemyDamage() {
        return false;
    }

    
    public int getDefenseBonus() {
        return 0;
    }

    @Override
    public String toString() {
        return name + " (" + duration + " turn(s))";
    }
}






