package src.action;

import effect.Stun;
import entity.Enemy;
import entity.Combatant;

public class ShieldBash extends SpecialSkill{

@Override
public void execute(Combatant attacker, List<Combatant> targets){
    if (targets == null || targets.isEmpty())
        return;

    Combatant target = targets.get(0);
    if (!target.isAlive()) 
        return;

    int damage = Math.max(0, attacker.getAtk() - target.getEffectiveDef());
    target.takeDamage(damage);
    
    if (target.isAlive()){
            target.addStatusEffect(new Stun());
    }
}

    @Override 
    public boolean isAreaOfEffect(){
        return false;
    }
    @Override
    public String getName(){
        return "Shield Bash";
    }
    @Override
    public String getDescription(){
        return "Deal BasicAttack damage and STUN the target for 2 turns.";
    }
}



