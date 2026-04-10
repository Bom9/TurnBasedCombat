package src.action;

import effect.DefenseBuff;
import entity.combatant;

public class Defend implements Action{
	@Override
	public void execute(Combatant attacker, List<Combatant> targets){
		attacker.addStatusEffect(new DefenseBuff());
	}


	@Override
	public String getName(){
		return "Defend";
	}

	@Override
	public String getDescription(){
		return "Increase DEF by " + DefenseBuff.BONUS + " for this turn and the next.";
    }
}
