package src.items;

import src.character.Combatant;
import src.effects.SmokeBombEffect;

import java.util.List;

public class SmokeBomb implements Item {

    private boolean used = false;

    @Override
    public String getName() {
        return "Smoke Bomb";
    }

    @Override
    public String getDescription() {
        return "Enemy attacks deal 0 damage this turn and the next turn.";
    }

    @Override
    public void use(Combatant user, List<Combatant> targets) {
        if (used) {
            System.out.println("Smoke Bomb has already been used.");
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

        user.addStatusEffect(new SmokeBombEffect());
        System.out.println(user.getName() + " used Smoke Bomb!");

        used = true;
    }
}