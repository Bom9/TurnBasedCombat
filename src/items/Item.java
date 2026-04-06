package src.items;

import src.character.Combatant;

public interface Item {

    String getName();
    String getDescription();
    void use (Combatant user,Combatant target);
}
