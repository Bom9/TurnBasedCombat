package src.items;

import src.character.Combatant;
import src.effects.ArcaneBlastBuff;

public class PowerStone implements Item {

    private static final int DURATION = 3;
    private boolean used = false;

    @Override
    public String getName() {
        return "Power Stone";
    }

    @Override
    public String getDescription() {
        return "Increases attack for a few turns.";
    }

    @Override
    public void use(Combatant user, Combatant target) {

        if (used) {
            System.out.println("Power Stone has already been used.");
            return;
        }

        if (target == null) {
            System.out.println("No target selected.");
            return;
        }

        if (!target.isAlive()) {
            System.out.println(target.getName() + " is defeated and cannot be buffed.");
            return;
        }

        ArcaneBlastBuff buff = new ArcaneBlastBuff(DURATION);
        target.addStatusEffect(buff);

        used = true;

        System.out.println(
            user.getName() + " used Power Stone on " + target.getName() +
            ". Attack increased for " + DURATION + " turns."
        );
    }
}

    


