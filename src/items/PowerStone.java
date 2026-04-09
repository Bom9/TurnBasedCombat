package src.items;

import src.character.Combatant;
import src.character.Player;

import java.util.List;

public class PowerStone implements Item {

    private boolean used = false;

    @Override
    public String getName() {
        return "Power Stone";
    }

    @Override
    public String getDescription() {
        return "Trigger the special skill once without affecting cooldown.";
    }

    @Override
    public void use(Combatant user, List<Combatant> targets) {
        if (used) {
            System.out.println("Power Stone has already been used.");
            return;
        }

        if (!(user instanceof Player player)) {
            System.out.println("Only players can use Power Stone.");
            return;
        }

        if (!player.isAlive()) {
            System.out.println(player.getName() + " is defeated and cannot use items.");
            return;
        }

        if (targets == null || targets.isEmpty()) {
            System.out.println("No valid target(s) selected.");
            return;
        }

        System.out.println(player.getName() + " used Power Stone!");
        player.getSpecialSkill().execute(player, targets);

        // IMPORTANT: do NOT change cooldown here

        used = true;
    }
}

    


