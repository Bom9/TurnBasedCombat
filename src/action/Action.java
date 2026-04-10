package src.action;

import src.entity.Combatant;

import java.util.List;
public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    String getName();

    String getDescription();

}