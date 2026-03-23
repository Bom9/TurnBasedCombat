import java.util.List;
public interface EnemyActionStrategy {
    Action decideAction(Combatant self, List<Combatant> enemies, List<Combatant> players);
}