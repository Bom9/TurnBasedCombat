package src.effects;


 /* DefenseBoost increases the combatant's defense by 10
 for 2 turns. */
 
public class DefenseBoost extends StatusEffect {

    private static final int BONUS = 10;
    private static final int DURATION = 2;

    public DefenseBoost() {
        super("Defense Boost", DURATION);
    }

    @Override
    public int getDefenseBonus() {
        return BONUS;
    }

    @Override
    public String toString() {
        return "Defense Boost +" + BONUS + " (" + duration + " turn" + (duration == 1 ? "" : "s") + " left)";
    }
}