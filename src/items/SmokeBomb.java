package src.items;

import character.Combatant;
import effects.SmokeBombEffect;
import java.util.List;


 /* SmokeBomb applies a protective effect that nullifies
  all incoming enemy damage for the current and next turn.*/
 
public class SmokeBomb extends Item {

    public SmokeBomb() {
        super("Smoke Bomb");
    }

    @Override
    public void use(Combatant user, List<Combatant> targets) {
        user.addStatusEffect(new SmokeBombEffect());
    }

    @Override
    public String getDescription() {
        return "Enemy attacks deal 0 damage this turn and the next.";
    }
}