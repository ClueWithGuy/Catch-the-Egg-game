package game;

public class GameState{
    private int score;
    private int missedEggs;
    private final int maxMissedEggs = 3;

    public GameState(){
	score = 0;
	missedEggs = 0;
    }

    public void addScore(int points){
	score += points;
    }

    public void subtractScore(int points){
	score -= points;
	if (score < 0){
	    score = 0;
	}
    }

    public int getScore(){
	return score;
    }

    public int getMissedEggs(){
	return missedEggs;
    }

    public int getMaxMissedEggs(){
	return maxMissedEggs;
    }

    public void eggMissed(){
	missedEggs++;
    }

    public void reset(){
	score = 0;
	missedEggs = 0;
    }

    
}
