package src.action;

public class ArcaneBlast implements SpecialSkill {
  private int cooldownTimer = 0;
  private static final int MAX_COOLDOWN = 3;

  @Override
  public void execute(Combatant user, List<Combatant> targets) {
    if (!isReady()) {
      System.out.println("Arcane Blast is on cooldown!");
      return;
    }

    System.out.println(user.getName() + " casts Arcane Blast on all enemies!");
        
    for (Combatant target : targets) {
      int damage = Math.max(0, user.getAttack() - target.getDefense());
      target.takeDamage(damage);
      System.out.println(target.getName() + " takes " + damage + " damage!");
      if (!target.isAlive()) {
        System.out.println(target.getName() + " was defeated by Arcane Blast!");
        user.addEffect(new ArcaneBlastBuff(999, 10)); 
      }
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
    return "Arcane Blast";
  }
}
  
