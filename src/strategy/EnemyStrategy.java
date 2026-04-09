package src.strategy;

import src.actions.Action;
import src.character.Combatant;
import java.util.List;

import java.util.List;
public interface EnemyStrategy {
    Action decideAction(Combatant self, List<Combatant> enemies, List<Combatant> players);
}
