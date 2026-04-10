package src.items;

import src.character.Combatant;
import java.util.List;


 //Abstract base class for all single-use items.
 
public abstract class Item {

    protected final String name;

    protected Item(String name) {
        this.name = name;
    }

    
     //Activates the item's effect.
     
     /* @param user The combatant using the item.
      @param targets Additional targets needed by some items.
     */
    public abstract void use(Combatant user, List<Combatant> targets);

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return name;
    }
}