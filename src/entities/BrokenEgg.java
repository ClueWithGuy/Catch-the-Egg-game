package entities;

public class BrokenEgg {

    private int x;
    private int y;
    private int points;
    private long createdTime;

    public BrokenEgg(int x, int y, int points) {
        this.x = x;
        this.y = y;
        this.points = points;
        this.createdTime = System.currentTimeMillis();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPoints() {
        return points;
    }

    public long getCreatedTime() {
        return createdTime;
    }
}
