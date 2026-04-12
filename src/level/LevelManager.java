package src.level;

import jdk.jshell.execution.JdiDefaultExecutionControl;
import src.character.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class LevelManager {
    //based on the input, initialise the respective SpawnConfig
    public SpawnConfig buildSpawnConfig(DifficultyLevel difficulty){
        return switch(difficulty){
            case EASY -> buildEasy();
            case MEDIUM -> buildMedium();
            case HARD -> buildHard();
        };
    }
    /*
    Setting the SpawnConfig with the correct amount of enemy according to the chosen difficulty level
     */
    protected SpawnConfig buildEasy(){
        List<Enemy> initial = new ArrayList<>();
        initial.add(new Goblin("Goblin A"));
        initial.add(new Goblin("Goblin B"));
        initial.add(new Goblin("Goblin C"));
        return new SpawnConfig(initial, Collections.emptyList());
    }
    protected SpawnConfig buildMedium(){
        List<Enemy> initial = new ArrayList<>();
        initial.add(new Goblin("Goblin"));
        initial.add(new Wolf("Wolf"));

        List<Enemy> backup = new ArrayList<>();
        backup.add(new Wolf("Wolf A"));
        backup.add(new Wolf("Wolf B"));
        return new SpawnConfig(initial, backup);
    }
    protected SpawnConfig buildHard(){
        List<Enemy> initial = new ArrayList<>();
        initial.add(new Goblin("Goblin A"));
        initial.add(new Goblin("Goblin B"));

        List<Enemy> backup = new ArrayList<>();
        backup.add(new Goblin("Goblin C"));
        backup.add(new Wolf("Wolf A"));
        backup.add(new Wolf("Wolf B"));
        return new SpawnConfig(initial, backup);
    }
    //Display the description of each difficulty level
    public String describeSpawn(DifficultyLevel difficulty){
        return switch(difficulty){
            case EASY -> "Initial Spawn: 3 Goblins";
            case MEDIUM -> "Inital Spawn: 1 Goblin + 1 Wolf | Backup Spawn: 2 Wolfs";
            case HARD -> "Initial Spawn: 2 Goblins | Backup: 1 Goblin = 2 Wolfs";
        };
    }
}