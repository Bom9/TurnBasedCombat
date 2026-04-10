package src.action;

public class BasicAttack implements Action {
	@Override
	public void execute(Combatant attacker, List<Combatant> targets){
		if (targets == null || targets.isEmpty())
			return;

		Combatant target = targets.get(0);
        if (!target.isAlive()) 
			return;

		if (attacker instanceof Enemy && target.isProtectedFromAttack()) {
			return;
		}	

		int damage = Math.max(0, attacker.getAtk() - target.getEffectiveDef());
        target.takeDamage(damage);
	}

	@Override
	public String getName(){
	return "Basic Attack";
	}

	@Override
	public String getDescription(){
		return "Attack a single target. Damage = max(0,ATK - DEF)."
	}
}
