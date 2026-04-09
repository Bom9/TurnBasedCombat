package src.effects;

import src.character.Combatant;
import src.character.Player;
import src.character.Wizard;

public class ArcaneBlastBuff extends StatusEffect {

    private static final int ATK_BONUS = 20;
    private static final int DURATION = 3;

    public ArcaneBlastBuff() {
        super("Arcane Blast Buff", DURATION);
    }

    @Override
    public void onApply(Combatant target) {
        if (target instanceof Wizard) {
            Wizard wizard = (Wizard) target;
            wizard.addAtkBonus(ATK_BONUS);
            System.out.println(wizard.getName() + " gains +" + ATK_BONUS + " ATK from Arcane Blast!");
        }
    }

    @Override
    public void onExpire(Combatant target) {
        if (target instanceof Wizard) {
            Wizard wizard = (Wizard) target;
            wizard.addAtkBonus(-ATK_BONUS);
            System.out.println(wizard.getName() + "'s Arcane Blast buff has expired.");
        }
    }
}