package src.action;

public class Defend implements Action{
  @Override
  public void execute(Combatant user, List<Combatant> targets){
    StatusEffect defenseBoost = new DefenseBoost(2,10);
    user.addEffect(defenseBoost);
    System.out.println(user.getName() + " defends! Defense increased by 10 for 2 turns.");
  }

  @Override
  public String getName(){
    return "Defend";
  }
}
