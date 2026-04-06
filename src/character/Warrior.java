package src.character;

import action.ShieldBash;

public class Warrior extends Player{
    public static final int BASE_HP = 260;
    public static final int BASE_ATK = 40;
    public static final int BASE_DEF = 20;
    public static final int BASE_SPD = 30;

    public Warrior(String name){
        super(name, BASE_HP, BASE_ATK, BASE_DEF, BASE_SPD, new ShieldBash());
    }

    public Warrior(){
        this(name: "Warrior");
    }
}
