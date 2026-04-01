package src.ui;

import src.effects.StatusEffect;
import src.actions.Action;
import src.character.Combatant;

import java.util.List;

public class CombatLogger {
    public void log(String message) {}
    public void logAction(Action action, Combatant user) {}
    public void logDamage(int damage, Combatant target) {}
    public void logHealing(int heal, Combatant target) {}
    public void logEffect(StatusEffect effect, Combatant target) {}
    public void displayCombatLog(String message) {}
    public void displayCombatStatus(List<Combatant> players, List<Combatant> enemies) {}
    public void displayGameOver(boolean victory) {}
}