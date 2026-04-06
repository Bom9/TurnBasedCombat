package src.actions;

public class BasicAttack implements Action {
  @Override
  public void execute(Combatant user, List<Combatant> targets){
    for (Combatant target : targets){
      int damage = Math.max(0, user.getAttack() - target.getDefense());
      target.takeDamage(damage);
      System.out.println(user.getName() + " uses Basic Attack on " + target.getName() + " for " + damage + " damage!");
    }
}

@Override
  public String getName(){
    return "Basic Attack";
  }
}
