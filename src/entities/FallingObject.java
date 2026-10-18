package entities;

public abstract class FallingObject{
    private int x;
    private int y;
    private int speed;

    public FallingObject(int x, int y, int speed){
	this.x = x;
	this.y = y;
	this.speed = speed;
    }

    public void fall(){
	y+= speed;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getSpeed(){
	return speed;
    }
}
