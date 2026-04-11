package src.action;

import src.character.Combatant;
import src.character.Wizard;
import java.util.List;

public class ArcaneBlast extends SpecialSkill{

	@Override
	public void execute(Combatant attacker, List<Combatant> targets){
		if (targets == null || targets.isEmpty()) return;

		for (Combatant target : targets){
            if (!target.isAlive()) continue;

            int damage = Math.max(0, attacker.getAttack() - target.getEffectiveDef());
            target.takeDamage(damage);
			
			if (!target.isAlive() && attacker instanceof Wizard){
                ((Wizard) attacker).addAtkBonus(10);
			}
		}
	}
	@Override 
	public boolean isAreaOfEffect(){
		return true;
	}

	@Override
	public String getDescription(){
		return "Deal BasicAttack damage to ALL enemies. Each kill grants +10 ATK permanently.";
    }

	@Override
	public String getName(){
		return "Arcane Blast";
	}
}
	



  
