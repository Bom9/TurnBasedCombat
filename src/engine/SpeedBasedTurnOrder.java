package src.engine;

import src.character.Combatant;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class SpeedBasedTurnOrder implements  TurnOrderStrategy{
    @Override
    public void sort(List<Combatant> combatants){
        /* sort in descending order using lambda expression
           based on the speed, if c1 is the first argument, then is ascending
         */
        combatants.sort((c1, c2) -> Integer.compare(c2.getSpeed(), c1.getSpeed()));
    }
}
