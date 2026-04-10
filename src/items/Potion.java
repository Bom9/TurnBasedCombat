package src.items;

import character.Combatant;
import java.util.List;


 //Potion heals the user by 100 HP, capped at max HP.
 
public class Potion extends Item {

    private static final int HEAL_AMOUNT = 100;

    public Potion() {
        super("Potion");
    }

    @Override
    public void use(Combatant user, List<Combatant> targets) {
        user.heal(HEAL_AMOUNT);
    }

    @Override
    public String getDescription() {
        return "Heals " + HEAL_AMOUNT + " HP (capped at max HP).";
    }
}

