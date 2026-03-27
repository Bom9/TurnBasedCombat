package src.actions;
import src.character.Combatant;

import java.util.List;
public interface EnemyActionStrategy {
    Action decideAction(Combatant self, List<Combatant> players);
}