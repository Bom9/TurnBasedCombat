package src.engine;

import src.character.Combatant;
import src.ui.GameConfig;
import java.util.List;

public class LevelManager {
    private GameConfig difficulty;
    protected SpawnConfig easySpawnConfig;
    protected SpawnConfig mediumSpawnConfig;
    protected SpawnConfig hardSpawnConfig;

    public List<Combatant> getInitialWave() { return null; }
    public List<Combatant> getBackupWave() { return null; }
    public void setDifficulty(GameConfig level) {}
    public GameConfig getDifficulty() { return null; }
}