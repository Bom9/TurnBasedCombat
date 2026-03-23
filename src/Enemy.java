public abstract class Enemy extends Combatant {
    protected EnemyActionStrategy actionStrategy;

    public Action decideAction() { return null; }
    public EnemyActionStrategy getActionStrategy() { return null; }
    public void setActionStrategy(EnemyActionStrategy strategy) {}
}