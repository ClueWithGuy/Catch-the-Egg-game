package entities;

public class EggFactory{
     public static Egg createRandomEgg(int x, int y,  int speed){
         int roll = (int)(Math.random() * 100);
 
         if (roll < 70){
             return new WhiteEgg(x, y , speed);
         }else if (roll < 95){
             return new NaturalEgg(x, y, speed);
         }else{
             return new GoldenEgg(x, y , speed);
         }
     }
 }
    
