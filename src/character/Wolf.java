package src.character;

import strategy.BasicEnemyStrategy;
public class Wolf extends Enemy {

    public static final int BASE_HP  = 40;
    public static final int BASE_ATK = 45;
    public static final int BASE_DEF = 5;
    public static final int BASE_SPD = 35;

    public Wolf(String label) {
        super(label, BASE_HP, BASE_ATK, BASE_DEF, BASE_SPD, new BasicEnemyStrategy());
    }

    public Wolf() {
        this("Wolf");
    }
}