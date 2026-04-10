package src.effects;


 //Stun prevents the affected combatant from acting for 2 turns.
 
public class Stun extends StatusEffect {

    private static final int STUN_DURATION = 2;

    public Stun() {
        super("Stun", STUN_DURATION);
    }

    @Override
    public boolean isTurnBlocking() {
        return true;
    }

    @Override
    public String toString() {
        return "STUNNED (" + duration + " turn" + (duration == 1 ? "" : "s") + " remaining)";
    }
}