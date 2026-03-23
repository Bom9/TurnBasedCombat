package src;

import java.util.List;
public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    String getName();
}