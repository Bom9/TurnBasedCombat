package src.level;

import src.character.Enemy;
import java.util.List;
/*
hold the instance of the configured wave of enemy based on the chosen difficulty
 */
public class SpawnConfig {
    //immutable, cannot be modified
    private final List<Enemy> initialSpawn;
    private final List<Enemy> backupSpawn;

    public SpawnConfig(List<Enemy> initialSpawn, List<Enemy> backupSpawn){
        /*
        this.initalSpawn = initialSpawn
        this method is acceptable but it holds the reference only, might be modified
         */
        this.initialSpawn = List.copyOf(initialSpawn); // List.copyOf return a read only list
        this.backupSpawn = List.copyOf(backupSpawn);
    }
    //returning the constructed list
    public List<Enemy> getInitialSpawn(){
        return initialSpawn;
    }
    public List<Enemy> getBackupSpawn(){
        return backupSpawn;
    }
}