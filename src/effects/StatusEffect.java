package src.effects;

import src.character.Combatant;


 //Abstract base class for all status effects.
 
public abstract class StatusEffect {

    protected String name;
    protected int duration;

    public StatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    
     //Called once when the effect is first applied.
     
    public void onApply(Combatant target) {
        // default: do nothing
    }

    
     //Called once when the effect expires.
     
    public void onExpire(Combatant target) {
        // default: do nothing
    }

    
     //Reduces remaining duration by one.
    public void decrementDuration() {
        if (duration > 0) {
            duration--;
        }
    }

    
     // Returns true if the effect has expired.
     
    public boolean isExpired() {
        return duration <= 0;
    }

    
     //Returns true if this effect blocks the holder's turn.
    
    public boolean isTurnBlocking() {
        return false;
    }

    
     //Returns additional defense granted by this effect.
     
    public int getDefenseBonus() {
        return 0;
    }

    
     //Returns true if this effect nullifies incoming enemy damage.
     
    public boolean nullifiesEnemyDamage() {
        return false;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return name + " (" + duration + " turn" + (duration == 1 ? "" : "s") + " left)";
    }
}




