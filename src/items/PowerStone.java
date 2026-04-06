package src.items;

import src.character.Combatant;
import src.effects.DefenseBoost;

public class PowerStone implements Item {
    private static final int BOOST_DURATION = 3;
    private boolean used = false;

    @Override
    public String getName() {
        return "Power Stone";
    }

    @Override
    public String getDescription() {
        return "Grants a temporary defense boost for " + BOOST_DURATION + " turns.";
    }

    @Override
    public void use(Combatant user, Combatant target) {
        if (used) {
            System.out.println("Power Stone has already been used.");
            return;
        }

        if (target == null) {
            System.out.println("No target selected for Power Stone.");
            return;
        }

        DefenseBoost effect = new DefenseBoost(BOOST_DURATION);
        target.addEffect(effect);
        effect.applyEffect(target);

        System.out.println(target.getClass().getSimpleName()
                + " used Power Stone and gained a defense boost!");

        used = true;
    }

    public boolean isUsed() {
        return used;
    }
}