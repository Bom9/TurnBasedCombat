package src.level;

public enum DifficultyLevel {
    EASY(1, "Easy"),
    MEDIUM(2,"Medium"),
    HARD(3,"Hard");

    private final int levelNumber;
    private final String displayName;

    DifficultyLevel(int levelNumber, String displayName){
        this.levelNumber = levelNumber;
        this.displayName = displayName;
    }
    public String getDisplayName(){
        return displayName;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
