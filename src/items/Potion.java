package src.items;

import src.character.Combatant;

public class Potion implements Item {

    private static final int HEAL_AMOUNT = 100;
    private boolean used = false;

    @Override
    public String getName() {
        return "Potion";
    }

    @Override
    public String getDescription() {
        return "Heals 100 HP, up to the target's max HP.";
    }

    @Override
    public void use(Combatant user, Combatant target) {

        if (used) {
            System.out.println("Potion has already been used.");
            return;
        }

        if (target == null) {
            System.out.println("No target selected.");
            return;
        }

        if (!target.isAlive()) {
            System.out.println(target.getName() + " is defeated and cannot be healed.");
            return;
        }

        int beforeHP = target.getHP();
        target.heal(HEAL_AMOUNT);
        int afterHP = target.getHP();
        int healedAmount = afterHP - beforeHP;

        used = true;

        System.out.println(
            user.getName() + " used Potion on " + target.getName() +
            ". HP: " + beforeHP + " -> " + afterHP +
            " (+" + healedAmount + ")"
        );
    }
}




