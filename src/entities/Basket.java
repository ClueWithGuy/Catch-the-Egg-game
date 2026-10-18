package entities;

public class Basket{
    private int x;
    private int y;
    private int width;
    private int height;
    private int moveAmount;

    public Basket(int x, int y, int width, int height, int moveAmount){
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.moveAmount = moveAmount;
    }

    public void moveLeft(){
	x-= moveAmount;
    }

    public void moveRight(){
	x+= moveAmount;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getWidth(){
	return width;
    }

    public int getHeight(){
	return height;
    }
}
