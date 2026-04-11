package src.character;

import java.util.List;

import src.action.Action;
import src.strategy.EnemyStrategy;

public abstract class Enemy extends Combatant {
    protected EnemyStrategy strategy;

    protected Enemy(String name, int maxHP, int atk, int def, int spd, EnemyStrategy strategy) {
        super(name, maxHP, atk, def, spd);
        this.strategy = strategy;
    }
    
    public Action decideAndGetAction(List<Combatant> players) {
        return strategy.decideAction(this, players);
    }

    public void setStrategy(EnemyStrategy strategy) {
        this.strategy = strategy;
    }

    public EnemyStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String getStatusString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name)
          .append(" | HP: ").append(hp).append("/").append(maxHP)
          .append(" | ATTACK: ").append(attack)
          .append(" | DEFEND: ").append(defend)
          .append(" | SPEED: ").append(speed);
        if (!isAlive) {
            sb.append(" [ELIMINATED]");
        } else if (isStunned()) {
            sb.append(" [STUNNED]");
        }
        if (!activeEffects.isEmpty() && isAlive) {
            sb.append(" Effects: ");
            activeEffects.forEach(e -> sb.append(e.getName()).append(" "));
        }
        return sb.toString();
    }
}