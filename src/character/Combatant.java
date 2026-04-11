package src.character;

import src.effects.StatusEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public abstract class Combatant {
    protected final String name;
    protected final int maxHP;
    protected int hp;
    protected int attack;
    protected int defend;
    protected final int speed;
    protected boolean isAlive;
    protected final List<StatusEffect> activeEffects;

    protected Combatant(String name, int maxHP, int atk, int def, int spd) {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attack = atk;
        this.defend = def;
        this.speed = spd;
        this.isAlive = true;
        this.activeEffects = new ArrayList<>();
    }

    public void takeDamage(int damage) {
        int actual = Math.max(0, damage);
        hp = Math.max(0, hp - actual);
        if (hp == 0) isAlive = false;
    }

    public void heal(int amount) {
        hp = Math.min(maxHP, hp + Math.max(0, amount));
    }
    
    public void addStatusEffect(StatusEffect effect) {
        effect.onApply(this);
        activeEffects.add(effect);
    }
    public void decrementNonStunEffects() {
        Iterator<StatusEffect> it = activeEffects.iterator();
        while (it.hasNext()) {
            StatusEffect effect = it.next();
            if (!effect.isTurnBlocking()) {
                effect.decrementDuration();
                if (effect.isExpired()) {
                    effect.onExpire(this);
                    it.remove();
                }
            }
        }
    }
    public boolean processStun() {
        Iterator<StatusEffect> it = activeEffects.iterator();
        while (it.hasNext()) {
            StatusEffect effect = it.next();
            if (effect.isTurnBlocking()) {
                effect.decrementDuration();
                if (effect.isExpired()) {
                    effect.onExpire(this);
                    it.remove();
                }
                return true;
            }
        }
        return false;
    }

    public boolean isProtectedFromAttack() {
        return activeEffects.stream().anyMatch(StatusEffect::nullifiesEnemyDamage);
    }

    public int getEffectiveDef() {
        int bonus = activeEffects.stream()
        .mapToInt(StatusEffect::getDefenseBonus)
        .sum();
        return defend + bonus;
    }
    public boolean isStunned() {
        return activeEffects.stream().anyMatch(StatusEffect::isTurnBlocking);
    }

    public String getName() {return name;}
    public int getMaxHP(){ return maxHP;}
    public int getHP(){ return hp;}
    public int getAttack(){ return attack;}
    public int getDefend(){ return defend;}
    public int getSpeed(){ return speed;}
    public boolean isAlive(){ return isAlive;}
    public List<StatusEffect> getActiveEffects(){ return activeEffects; }

    public void setAttack(int attack) { this.attack = attack; }

    public abstract String getStatusString();

    @Override
    public String toString() {
        return name + " [HP: " + hp + "/" + maxHP + "]";
    }
}