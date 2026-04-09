package src.effects;

import src.entity.Combatant;

public class SmokeBombEffect extends StatusEffect {

    private static final int DURATION = 2;

    public SmokeBombEffect() {
        super("Smoke Bomb", DURATION);
    }

    @Override
    public void onApply(Combatant target) {
        System.out.println(target.getName() + " is protected by Smoke Bomb! Enemy attacks will deal 0 damage this turn and the next turn.");
    }

    @Override
    public void onExpire(Combatant target) {
        System.out.println("Smoke Bomb effect on " + target.getName() + " has expired.");
    }

    @Override
    public boolean nullifiesEnemyDamage() {
        return true;
    }
}
