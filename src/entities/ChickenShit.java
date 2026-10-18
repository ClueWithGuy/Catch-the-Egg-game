package entities;

public class ChickenShit extends FallingObject{
    private int penalty;

    public ChickenShit(int x, int y , int speed, int penalty ){
	super(x, y, speed);
	this.penalty = penalty;
    }

    public int getPenalty(){
	return penalty;
    }
}
