package src.character;

import src.items.Item;

import java.util.ArrayList;
import java.util.List;

import src.action.SpecialSkill;

public abstract class Player extends Combatant {
    protected final SpecialSkill specialSkill;
    protected int skillCooldown;

    public static final int SKILL_COOLDOWN_MAX = 3;

    protected final List<Item> inventory;
    protected final List<Item> initialInventory;

    protected Player(String name, int maxHP, int atk, int def, int spd, SpecialSkill specialSkill){
        super(name, maxHP, atk, def, spd);
        this.specialSkill = specialSkill;
        this.skillCooldown = 0;
        this.inventory = new ArrayList<>();
        this.initialInventory = new ArrayList<>();
    }

    public boolean isSkillReady(){
        return skillCooldown == 0;
    }

    public void triggerSkillCooldown(){
        skillCooldown = SKILL_COOLDOWN_MAX;
    }

    public void decrementSkillCooldown(){
        if (skillCooldown > 0) skillCooldown--;
    }

    public int getSkillCooldown(){ 
        return skillCooldown; 
    }

    public void addItem(Item item){

        inventory.add(item);
        initialInventory.add(item);
    }

    public List<Item> getInventory(){
        return inventory;
    }
    public List<Item> getInitialInventory(){
        return initialInventory;
    }

    public boolean hasItems(){
        return !inventory.isEmpty();
    }

    public Item removeItem(int index){
        return inventory.remove(index);
    }

    public SpecialSkill getSpecialSkill(){
        return specialSkill;
    }

    @Override
    public String getStatusString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name)
          .append(" | HP: ").append(hp).append("/").append(maxHP)
          .append(" | ATK: ").append(atk)
          .append(" | DEF: ").append(def)
          .append(" (effective: ").append(getEffectiveDef()).append(")")
          .append(" | SPD: ").append(spd)
          .append(" | Skill CD: ").append(skillCooldown);
        if (!activeEffects.isEmpty()) {
            sb.append(" | Effects: ");
            activeEffects.forEach(e -> sb.append(e.getName()).append(" "));
        }
        if (!inventory.isEmpty()) {
            sb.append(" | Items: ");
            inventory.forEach(i -> sb.append(i.getName()).append(" "));
        }
        return sb.toString();
    }
}