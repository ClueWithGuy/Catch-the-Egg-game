package game;

import java.util.ArrayList;
import java.util.Iterator;

import entities.Egg;
import entities.Basket;
import entities.Chicken;
import entities.ChickenShit;
import entities.SplashedShit;
import entities.BrokenEgg;
import entities.FallingObject;

import exceptions.GameException;

public class Game{
    private int height;
    private int width;
    private final int chickenY = 50;
    private final int chickenSpacing = 100;
    private final int chickenStartX = 50;
    
    private long lastEggSpawnTime;
    private long lastShitSpawnTime;
    private final long eggSpawnInterval = 1500;
    private final long shitSpawnInterval = 2500;
    
    private GameState state;
    private Basket basket;
    private Chicken[] chickens;
    private ArrayList<FallingObject> fallingObjects;
    private ArrayList<BrokenEgg> brokenEggs;
    private ArrayList<SplashedShit> splashedShits;
    private Spawner spawner;
    private long gameStartTime;

    public Game(){
	height = 600;
	width = 600;
	gameStartTime = 0;
	lastEggSpawnTime = 0;
	lastShitSpawnTime = 0;
	state = new GameState();
	basket = new Basket(300, 500, 120, 50, 25);
	chickens = new Chicken[6];

	for(int i = 0; i < chickens.length; i++){
	    chickens[i] = new Chicken( chickenStartX + i * chickenSpacing, chickenY );
	}

	spawner = new Spawner(chickens, 5);
	
	fallingObjects = new ArrayList<>();
	brokenEggs = new ArrayList<>();
	splashedShits = new ArrayList<>();
    }

    public void startGame() {
	gameStartTime = System.currentTimeMillis();
	lastEggSpawnTime = gameStartTime;
	lastShitSpawnTime = gameStartTime;
    }

    public GameState getState(){
	return state;
    }

    public Basket getBasket(){
	return basket;
    }

    public Chicken[] getChickens(){
	return chickens;
    }

    public ArrayList<FallingObject> getFallingObjects(){
	return fallingObjects;
    }

    public ArrayList<BrokenEgg> getBrokenEggs(){
	return brokenEggs;
    }

    public ArrayList<SplashedShit> getSplashedShits(){
	return splashedShits;
    }

    public Spawner getSpawner(){
	return spawner;
    }

    public void spawnEgg(){
	if(!spawner.isEggFalling()){
	    Egg egg = spawner.spawnEgg();
	    fallingObjects.add(egg);
	}
    }

    public void spawnShit(){
	ChickenShit shit = spawner.spawnShit();
	fallingObjects.add(shit);
    }

    public void updateObjects() {
	Iterator<FallingObject> iterator = fallingObjects.iterator();
    while (iterator.hasNext()) {
        FallingObject object = iterator.next();

        object.fall();

        if (isCaught(object)) {
            catchObject(object);
            iterator.remove();

        } else if (isObjectAtBottom(object)) {

            if (object instanceof Egg) {
                Egg egg = (Egg) object;

                state.eggMissed();
                spawner.eggFinished();

                brokenEggs.add(
                    new BrokenEgg(
                        egg.getX(),
                        height - 40,
                        egg.getPoints()
                    )
                );

            } else if (object instanceof ChickenShit) {
                splashedShits.add(
                    new SplashedShit(
                        object.getX(),
                        height - 40
                    )
                );
            }

            iterator.remove();
        }
    }
    }

    public int getHeight(){
	return height;
    }

    public boolean isObjectAtBottom(FallingObject object){
	return object.getY() + 40 >= height;
    }

    public boolean isGameOver(){
	return state.getMissedEggs() >= state.getMaxMissedEggs();
    }

    public boolean isCaught(FallingObject object){
	int eggLeft = object.getX();
	int eggRight = eggLeft + 40;
	int eggTop = object.getY();
	int eggBottom = eggTop + 40;

	int basketLeft = basket.getX();
	int basketRight = basketLeft + basket.getWidth();
	int basketTop = basket.getY();

	int overlapLeft = Math.max(eggLeft, basketLeft);
	int overlapRight = Math.min(eggRight, basketRight);
	int overlap = overlapRight - overlapLeft;
	boolean enoughHorizontalOverlap = overlap >= 30;

	boolean hittingBasketTop =
	    eggBottom >= basketTop &&
	    eggTop < basketTop;

	return enoughHorizontalOverlap && hittingBasketTop;
    }


    public void catchObject(FallingObject object){
	if(object instanceof Egg){
	    Egg egg = (Egg) object;
	    state.addScore(egg.getPoints());
	    updateDifficulty();
	    spawner.eggFinished();
	}else if(object instanceof ChickenShit){
	    ChickenShit shit = (ChickenShit) object;
	    state.subtractScore(shit.getPenalty());
	}
    }

    public void update(){
	if(isGameOver()){
	    return;
	}

	if(gameStartTime == 0){
	    return;
	}

	long currentTime = System.currentTimeMillis();
	if(currentTime - gameStartTime < 3000){
	    return;
	}

	if (!spawner.isEggFalling() && currentTime - lastEggSpawnTime >= eggSpawnInterval){
	    spawnEgg();
	    lastEggSpawnTime = currentTime;
	}

	if (currentTime - lastShitSpawnTime >= shitSpawnInterval){
	    spawnShit();
	    lastShitSpawnTime = currentTime;
	}
	
	updateObjects();
    }

    public void updateDifficulty(){
	int newSpeed = 5 + state.getScore() / 70;
	spawner.setSpeed(newSpeed);
    }

    public void moveBasketLeft(){
	if(basket.getX() > 0){
	    basket.moveLeft();
	}
    }
    public void moveBasketRight(){
	if(basket.getX() + basket.getWidth() < width){
	    basket.moveRight();
	}
    }

    public void reset() {
	state.reset();
	fallingObjects.clear();
	brokenEggs.clear();
	splashedShits.clear();
	basket = new Basket(300, 500, 120, 50, 25);
	spawner = new Spawner(chickens, 5);

	lastEggSpawnTime = System.currentTimeMillis();
	lastShitSpawnTime = System.currentTimeMillis();
    }
    

    public void validateGame() throws GameException{
	if (width <= 0 || height <=0 ){
	    throw new GameException("Game dimensions must be positive");
	}
    }

}

