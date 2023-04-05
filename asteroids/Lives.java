/**
 * class that sets up the lives mechanic for the ship.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 *
 */

public class Lives extends StaticObject {
    private int livesRemaining;

    /**
     * Lives constructor that gives it its location on the screen.
     * 
     * @param position value
     */

    public Lives(Point position) {
        super(position);
        this.livesRemaining = 3;
    }

    /**
     * update does nothing because its not moving.
     */

    public void update() {

    }

    /**
     * draws and the given position and prints out Lives: and the remaining
     * lives.
     */

    public void draw() {
        StdDraw.text(position.getX(), position.getY(),
                String.format("Lives: %d", this.livesRemaining));
    }

    /**
     * gets the lives.
     * 
     * @return lives value
     */

    public int getLives() {
        return livesRemaining;
    }

    /**
     * removes a life from the lives remaining.
     */

    public void removeLife() {
        livesRemaining = livesRemaining - 1;
    }
}
