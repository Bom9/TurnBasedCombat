package src.actions;

public class BasicEnemyStrategy implements EnemyActionStrategy {
  @Override
  public Action decideAction(Combatant self, List<Combatant> enemies, List<Combatant> players) {
    return new EnemyBasicAttack();
  }
}
