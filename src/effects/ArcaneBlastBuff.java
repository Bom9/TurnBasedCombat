package src.effects;


 /* ArcaneBlastBuff increases the combatant's attack damage
 for a few turns.*/
 
public class ArcaneBlastBuff extends StatusEffect {

    private static final int BONUS = 20;   // attack boost amount
    private static final int DURATION = 3; // lasts 3 turns

    public ArcaneBlastBuff() {
        super("Arcane Blast Buff", DURATION);
    }

    
    // Returns additional attack granted by this effect.
     
    public int getAttackBonus() {
        return BONUS;
    }

    @Override
    public String toString() {
        return "Arcane Blast Buff +" + BONUS + " (" + duration + " turn" + (duration == 1 ? "" : "s") + " left)";
    }
}