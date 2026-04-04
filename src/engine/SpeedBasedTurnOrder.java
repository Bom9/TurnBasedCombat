package src.engine;

import src.character.Combatant;
import java.util.List;
import java.util.ArrayList;

public class SpeedBasedTurnOrder implements  TurnOrderStrategy{
    @Override
    public List<Combatant> sortCombatants(List<Combatant> combatants){
        /*
            sort in descending order using lambda expression
           based on the speed, if c1 is the first argument, then is ascending
           return the sorted list
         */
        List<Combatant> sorted = new ArrayList<>(combatants);
        sorted.sort((c1, c2) -> Integer.compare(c2.getSpeed(), c1.getSpeed()));
        return sorted;
    }
}
