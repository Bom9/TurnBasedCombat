package src.engine;
import src.character.Combatant;

import java.util.List;
public interface TurnOrderStrategy {
    List<Combatant> sort(List<Combatant> combatants);
}