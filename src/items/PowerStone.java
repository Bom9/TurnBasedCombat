package src.items;

import character.Combatant;
import character.Player;
import java.util.List;


 //PowerStone triggers the player's special skill once without affecting cooldown. 
 
public class PowerStone extends Item {

    public PowerStone() {
        super("Power Stone");
    }

    @Override
    public void use(Combatant user, List<Combatant> targets) {
        if (!(user instanceof Player)) {
            return;
        }

        Player player = (Player) user;
        player.getSpecialSkill().execute(player, targets);
    }

    @Override
    public String getDescription() {
        return "Trigger Special Skill once for free (does not affect cooldown).";
    }
}

    


