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
            System.out.println("No target selected for Potion.");
            return;
        }

        int currentHp = target.getHp();
        target.heal(HEAL_AMOUNT);
        int healedAmount = target.getHp() - currentHp;

        used = true;

        System.out.println("Potion used. Restored " + healedAmount + " HP.");
    }

    public boolean isUsed() {
        return used;
    }
}
