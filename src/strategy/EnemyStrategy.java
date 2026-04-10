package src.strategy;

import src.action.Action;
import src.character.Combatant;

import java.util.List;
public interface EnemyStrategy {
    Action decideAction(Combatant attacker, List<Combatant> players);
}
