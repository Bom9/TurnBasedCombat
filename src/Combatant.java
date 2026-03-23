import java.util.List;
public abstract class Combatant {
    protected int maxHp;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected List<Item> inventory;
    protected List<StatusEffect> activeEffects;
    protected String name;

    public abstract void takeTurn();
    
    public void takeDamage(int damageAmount) {}
    public void heal(int healAmount) {}
    public boolean isAlive() { return false; }
    public void updateEffects() {}
    public int getSpeed() { return 0; }
    public int getHp() { return 0; }
    public int getMaxHp() { return 0; }
    public int getAttack() { return 0; }
    public int getDefense() { return 0; }
    public List<Item> getInventory() { return null; }
    public void addEffect(StatusEffect effect) {}
    public void removeEffect(StatusEffect effect) {}
}