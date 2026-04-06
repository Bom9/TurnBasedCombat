package src.effects;

import src.character.Combatant;

public abstract class StatusEffect{
    protected String name;
    protected int durationTurns;
    protected int currentDuration;

    public StatusEffect(String name, int durationTurns){
    this.name=name;
    this.durationTurns=durationTurns;
    this.currentDuration=durationTurns;}

    public String getName(){
    return name;}

    public int getDurationTurns(){
        return durationTurns;

    }

    public int getCurrentDuration(){
        return currentDuration;

    }

    public void decrementDuration(){
        if(currentDuration>0){
            currentDuration--;
        }
    }

    public boolean isExpired(){
        return currentDuration<=0;
    }

    public abstract void applyEffect(Combatant target);

    public abstract void removeEffect(Combatant target);

    public void onTurnStart(Combatant target){}



}






