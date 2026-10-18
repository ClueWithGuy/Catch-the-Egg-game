package entities;

public abstract class Egg extends FallingObject{
    private int points;

    Egg(int x, int y, int speed, int points){
	
	super(x, y, speed);
	this.points = points;
    }

    public int getPoints(){
	return points;
    }
}
