package src.action;

import src.character.Combatant;

import java.util.List;
public interface Action {
    void execute(Combatant attacker, List<Combatant> targets);
    String getName();

    String getDescription();

}