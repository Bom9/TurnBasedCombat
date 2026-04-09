package src.items;

import src.character.Combatant;
import java.util.List;

public class Potion implements Item {

    private static final int HEAL_AMOUNT = 100;
    private boolean used = false;

    @Override
    public String getName() {
        return "Potion";
    }

    @Override
    public String getDescription() {
        return "Heal 100 HP.";
    }

    @Override
    public void use(Combatant user, List<Combatant> targets) {
        if (used) {
            System.out.println("Potion has already been used.");
            return;
        }

        if (user == null) {
            System.out.println("No user selected.");
            return;
        }

        if (!user.isAlive()) {
            System.out.println(user.getName() + " is defeated and cannot use items.");
            return;
        }

        int beforeHP = user.getHP();
        user.heal(HEAL_AMOUNT);
        int afterHP = user.getHP();

        System.out.println(user.getName() + " used Potion! HP: " + beforeHP + " -> " + afterHP);

        used = true;
    }
}


