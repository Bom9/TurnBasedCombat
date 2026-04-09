package src.effects;

import src.entity.Combatant;

public class Stun extends StatusEffect {

    private static final int STUN_DURATION = 2;

    public Stun() {
        super("Stun", STUN_DURATION);
    }

    @Override
    public void onApply(Combatant target) {
        System.out.println(target.getName() + " is stunned and cannot act for this turn and the next turn!");
    }

    @Override
    public void onExpire(Combatant target) {
        System.out.println(target.getName() + " is no longer stunned.");
    }

    @Override
    public boolean isTurnBlocking() {
        return true;
    }
}