package src;
public interface Item {
    String getName();
    void use(Combatant user, Combatant target);
    String getDescription();
}