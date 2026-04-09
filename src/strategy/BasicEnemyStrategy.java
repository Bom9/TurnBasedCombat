package src.strategy;

import src.actions.Action;
import src.actions.EnemyActionStrategy;
import src.actions.EnemyBasicAttack;

public class BasicEnemyStrategy implements EnemyStrategy {
    @Override
    public Action decideAction(Combatant self, List<Combatant> enemies, List<Combatant> players) {
        return new EnemyBasicAttack();
    }
}