package entities;

class WhiteEgg extends Egg{
    public WhiteEgg(int x , int y , int speed){
	super(x, y , speed, 5);
    }
}

class NaturalEgg extends Egg{
    public NaturalEgg(int x, int y, int speed){
	super(x, y, speed, 10);
    }
}

class GoldenEgg extends Egg{
    public GoldenEgg(int x , int y, int speed){
	super(x, y, speed, 25);
    }
}

