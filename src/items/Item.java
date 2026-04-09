package src.items;

import src.character.Combatant;
import java.util.List;

public interface Item {
    String getName();
    String getDescription();
    void use(Combatant user, List<Combatant> targets);
}