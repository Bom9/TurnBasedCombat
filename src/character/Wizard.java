package src.character;

import src.action.ArcaneBlast;

public class Wizard extends Player{
    public static final int BASE_HP = 200;
    public static final int BASE_ATK = 50;
    public static final int BASE_DEF = 10;
    public static final int BASE_SPD = 20;

    private int atkBonus = 0;

    public Wizard(String name){
        super(name, BASE_HP, BASE_ATK, BASE_DEF, BASE_SPD, new ArcaneBlast());
    }

    public Wizard(){
        this(name: "Wizard");
    }
    public void addAtkBonus(int amount){
        atkBonus += amount;
        atk += amount;
    }

    public int getAtkBonus(){
        return atkBonus;
    }

    @Override
    public String getStatusString(){
        String base = super.getStatusString();
        if (atkBonus>0) base += " | Arcane Bonus ATK: +" + atkBonus;
        return base;
    }
    
}
