public class CombatLogger {
    public void log(String message) {}
    public void logAction(Action action, Combatant user) {}
    public void logDamage(int damage, Combatant target) {}
    public void logHealing(int heal, Combatant target) {}
    public void logEffect(StatusEffect effect, Combatant target) {}
}