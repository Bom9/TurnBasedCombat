package src.items;

import src.character.Combatant;
import src.effects.SmokeBombEffect;

public class SmokeBomb implements Item {
    private static final int EFFECT_DURATION = 2;
    private boolean used = false;

    @Override
    public String getName() {
        return "Smoke Bomb";
    }

    @Override
    public String getDescription() {
        return "Reduces the target's attack temporarily for " + EFFECT_DURATION + " turns.";
    }

    @Override
    public void use(Combatant user, Combatant target) {
        if (used) {
            System.out.println("Smoke Bomb has already been used.");
            return;
        }

        if (target == null) {
            System.out.println("No target selected for Smoke Bomb.");
            return;
        }

        SmokeBombEffect effect = new SmokeBombEffect(EFFECT_DURATION);
        target.addEffect(effect);
        effect.applyEffect(target);

        System.out.println(target.getClass().getSimpleName()
                + " is affected by Smoke Bomb!");

        used = true;
    }

    public boolean isUsed() {
        return used;
    }
}