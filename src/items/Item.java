package src.items;

import src.character.Combatant;

public interface Item {
    String getName();
    void use(Combatant user, Combatant target);
    String getDescription();
}