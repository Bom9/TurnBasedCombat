package src.character;
import src.items.Item;
import src.effects.StatusEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public abstract class Combatant {
    protected final String name;
    protected final int maxHP;
    protected int hp;
    protected int atk;
    protected int def;
    protected final int spd;
    protected boolean isAlive;
    protected final List<StatusEffect> activeEffects;

    protected Combatant(String name, int maxHP, int atk, int def, int spd) {
        this.name         = name;
        this.maxHP        = maxHP;
        this.hp           = maxHP;
        this.atk          = atk;
        this.def          = def;
        this.spd          = spd;
        this.isAlive      = true;
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
        return def + bonus;
    }
    public boolean isStunned() {
        return activeEffects.stream().anyMatch(StatusEffect::isTurnBlocking);
    }

    public String             getName()         { return name;         }
    public int                getMaxHP()        { return maxHP;        }
    public int                getHP()           { return hp;           }
    public int                getAtk()          { return atk;          }
    public int                getDef()          { return def;          }
    public int                getSpd()          { return spd;          }
    public boolean            isAlive()         { return isAlive;      }
    public List<StatusEffect> getActiveEffects(){ return activeEffects; }

    public void setAtk(int atk) { this.atk = atk; }

    public abstract String getStatusString();

    @Override
    public String toString() {
        return name + " [HP: " + hp + "/" + maxHP + "]";
    }
}