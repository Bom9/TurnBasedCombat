package src.actions;

public class ShieldBash implements SpecialSkill{
  private in cooldownTimer = 0;
  private static final int MAX_COOLDOWN = 3;
  
  @Override
  public void execute(Combatant user, List<COmbatant> targets){
    if (!isReady()){
      System.out.println("Shield Bash is on cooldown!");
      return;
    }

    for (Combatant target : targets) {
      int damage = Math.max(0, user.getAttack() - target.getDefense());
      target.takeDamage(damage);
      
      target.addEffect(new Stun(2));
      System.out.println(user.getName() + " uses Shield Bash! " + target.getName() + " takes " + damage + " damage and is Stunned!");
    }
    resetCooldown();
  }

  @Override
  public int getCooldownTimer() { return cooldownTimer; }
  
  @Override
  public void reduceCooldown() {
    if (cooldownTimer > 0) cooldownTimer--;
  }

  @Override
  public void resetCooldown() {
    cooldownTimer = MAX_COOLDOWN;
  }

  @Override
  public boolean isReady() {
    return cooldownTimer == 0;
  }

  @Override
  public String getName() {
    return "Shield Bash";
  }
}
