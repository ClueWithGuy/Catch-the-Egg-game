package game;

import entities.Chicken;
import entities.Egg;
import entities.EggFactory;
import entities.ChickenShit;

public class Spawner{
    private Chicken[] chickens;
    private int speed;
    private boolean eggFalling;

    public Spawner(Chicken[] chickens, int speed){
	this.chickens = chickens;
	this.speed= speed;
	eggFalling = false;
    }

    public void setSpeed(int speed){
	this.speed = speed;
    }

    public int getSpeed(){
	return speed;
    }

    public Chicken[] getChickens(){
	return chickens;
    }

    public Chicken getRandomChicken(){
	int index = (int)(Math.random() * chickens.length);
	return chickens[index];
    }

    public Egg spawnEgg(){
	Chicken chicken = getRandomChicken();
	eggFalling = true;
	return EggFactory.createRandomEgg(chicken.getX(), chicken.getY(), speed);
    }

    public ChickenShit spawnShit(){
	Chicken chicken = getRandomChicken();
	return new ChickenShit(chicken.getX(), chicken.getY(), speed, 25);
    }

    public boolean isEggFalling(){
	return eggFalling;
    }

    public void eggFinished(){
	eggFalling = false;
    }
    
}
