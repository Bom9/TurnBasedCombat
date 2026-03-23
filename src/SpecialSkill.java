//import java.util.List; //Note: uncomment this import when using
public interface SpecialSkill extends Action {
    int getCooldownTimer();
    void reduceCooldown();
    void resetCooldown();
    boolean isReady();
}