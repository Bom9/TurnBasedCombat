package src;
public abstract class Player extends Combatant {
    protected SpecialSkill specialSkill;
    protected String classType;

    public SpecialSkill getSpecialSkill() { return null; }
    public void useSpecialSkill() {}
    public void useItem(Item item) {}
    public void equipItem(Item item) {}
    public String getClassType() { return null; }
}