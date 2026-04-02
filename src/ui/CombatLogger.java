package src.ui;

import src.effects.StatusEffect;
import src.actions.Action;
import src.character.Combatant;
import java.util.List;

//This whole class is for output of all the things that happened in the game
//(Massive Print statement)

public class CombatLogger {
    public void logAction(Action action, Combatant user)
    {
        System.out.println(">>> " + user.getName() + " uses " + action.getName() + "!");
    }
    public void logDamage(int damage, Combatant target)
    {
        System.out.println(target.getName() + " takes " + damage + "damage.");
        if (!target.isAlive())
        {
            System.out.println(target.getName() + " has been ELIMINATED!");
        }
    }

    public void logHealing(int heal, Combatant target)
    {
        System.out.println(target.getName() + " recovers " + heal + " HP.");
    }
    public void logEffect(StatusEffect effect, Combatant target)
    {
        System.out.println(target.getName() + " is now affected by " + effect.getName() + ".");
    }



    public void displayCombatLog(String message)
    {
        System.out.println(">>>  "+ message);
    }

    public void displayCombatStatus(List<Combatant> players, List<Combatant> enemies)
    {
        System.out.println("\n----- CURRENT BATTLE STATUS -----");
        System.out.println("\nPLAYERS:");
        for (Combatant p : players)
        {
            String status = p.isAlive() ? "":"[ELIMINATED]";
            System.out.println("- " + p.getName() + " | HP: )" + p.getHp() + status);
        }

        System.out.println("\nENEMIES:");
        for (Combatant e : enemies)
        {
            String status = e.isAlive() ? "":"[ELIMINATED]";
            System.out.println("- " + e.getName() + " | HP: )" + e.getHp() + status);
        }

        System.out.println("---------------------------------\n");
    }
    public void displayGameOver(boolean victory, int rounds, int finalHp, int enemiesLeft)
    {
        System.out.println("\n==============================");
        if (victory){
            System.out.println("Congratulations, you have defeated all your enemies.");
            System.out.println("Statistics: Remaining HP: "+ finalHp + " | Total Rounds: " + rounds);
        } else {
            System.out.println("Defeated. Don't give up, Try Again!");
            System.out.println("Statistics: Enemies Remaining: "+ enemiesLeft+ " | Total Rounds: " + rounds);
        }
        System.out.println("==============================\n");
    }
}