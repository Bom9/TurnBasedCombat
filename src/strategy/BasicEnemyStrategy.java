package src.strategy;

import src.action.Action;
import src.action.BasicAttack;
import src.character.Combatant;
import java.util.List;

public class BasicEnemyStrategy implements EnemyStrategy {

  private final BasicAttack basicAttack = new BasicAttack();
  @Override
  public Action decideAction(Combatant attacker, List<Combatant> players) {
    return basicAttack;
  }
}
