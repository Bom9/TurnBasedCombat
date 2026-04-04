package src.engine;
import src.character.Combatant;

import java.util.List;
/*
an interface having the method/methods to sort the combatant
currently only sorting based on speed,
could be extended by introducing new method for the implementing class to implement
 */
public interface TurnOrderStrategy {
    /*
    interface method for different sub class to implement
    currently, only one sub class, SpeedBasedTurnOrder
     */
    List<Combatant> sortCombatants(List<Combatant> combatants);
}