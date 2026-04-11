//package src.action;
//
//public class EnemyBasicAttack implements Action{
//  @Override
//  public void execute(Combatant user, List<Combatant> targets){
//    for (Combatant target : targets){
//      int damage = Math.max(0, user.getAttack() - target.getDefense());
//      target.takeDamage(damage);
//      System.out.println("Enemy " + user.getNAme() + " attacks " + target.getName() + " for " + damage + " damage!");
//    }
//  }
//
//  @Override
//  public String getName(){
//    return "Enemy Basic Attack";
//  }
//}
