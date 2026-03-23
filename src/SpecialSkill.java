import java.util.List;
public interface SpecialSkill extends Action {
    int getCooldownTimer();
    void reduceCooldown();
    void resetCooldown();
    boolean isReady();
}