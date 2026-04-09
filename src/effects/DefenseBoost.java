package src.effects;

import src.character.Combatant;

public class DefenseBoost extends StatusEffect {

    private static final int DEFENSE_BONUS = 10;
    private static final int DURATION = 2;

    public DefenseBoost() {
        super("Defense Boost", DURATION);
    }

    @Override
    public void onApply(Combatant target) {
        System.out.println(target.getName() + " gains +" + DEFENSE_BONUS
                + " defense for this turn and the next turn.");
    }

    @Override
    public void onExpire(Combatant target) {
        System.out.println("Defense Boost on " + target.getName() + " has expired.");
    }

    @Override
    public int getDefenseBonus() {
        return DEFENSE_BONUS;
    }
}