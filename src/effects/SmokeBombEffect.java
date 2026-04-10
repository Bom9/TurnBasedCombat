package src.effects;


 /*SmokeBombEffect nullifies all incoming enemy damage
 for the current turn and the next turn.*/
public class SmokeBombEffect extends StatusEffect {

    private static final int DURATION = 2;

    public SmokeBombEffect() {
        super("Smoke Bomb", DURATION);
    }

    @Override
    public boolean nullifiesEnemyDamage() {
        return true;
    }

    @Override
    public String toString() {
        return "Smoke Bomb Shield (" + duration + " turn" + (duration == 1 ? "" : "s") + " left)";
    }
}