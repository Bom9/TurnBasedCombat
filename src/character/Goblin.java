package src.entity;
import strategy.BasicEnemyStrategy;

public class Goblin extends Enemy{

    public static final int BASE_HP  = 55;
    public static final int BASE_ATK = 35;
    public static final int BASE_DEF = 15;
    public static final int BASE_SPD = 25;

    public Goblin(String label) {
        super(label, BASE_HP, BASE_ATK, BASE_DEF, BASE_SPD, new BasicEnemyStrategy());
    }

    public Goblin() {
        this("Goblin");
    }
}

