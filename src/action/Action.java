package src.action;

import src.character.Combatant;

import java.util.List;
public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    String getName();
}