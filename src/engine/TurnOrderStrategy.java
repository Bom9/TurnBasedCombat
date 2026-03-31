package src.engine;
import src.character.Combatant;

import java.util.List;
public interface TurnOrderStrategy {
    void sort(List<Combatant> combatants);
}