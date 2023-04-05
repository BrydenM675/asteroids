import java.awt.Color;

/**
 * Bullet class for the bullet game piece.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 *
 */

public class Bullet extends MovingObject {
    private static final int LIFETIME = 20;
    private int lifetime;

    /**
     * Bullet constructor that gives it a pose that is the ships direction also
     * setting its lifetime to 20 time steps.
     * 
     * @param pose value
     */

    public Bullet(Pose pose) {
        super(pose, 1.5);
        this.lifetime = LIFETIME;

    }

    /**
     * draws the bullet if its not destroyed with the x and y and radius of 1.5.
     */

    public void draw() {
        if (!destroyed) {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.filledCircle(pose.getX(), pose.getY(), 1.5);
        }
    }

    /**
     * move method that sets the pose to the heading of the ship and 20 pixels
     * per time step also the wrapping logic.
     */

    public void move() {
        pose = pose.move(new Vector2D(pose.getHeading(), 20));
        if (pose.getX() > 480) {
            pose = pose.newX(0);
        }
        if (pose.getX() < 0) {
            pose = pose.newX(480);
        }
        if (pose.getY() > 480) {
            pose = pose.newY(0);
        }
        if (pose.getY() < 0) {
            pose = pose.newY(480);
        }
    }

    /**
     * overides the update method to set up the lifetime of the bullet.
     */

    @Override
    public void update() {
        move();
        this.lifetime -= 1;
        if (this.lifetime == 0) {
            destroy();
        }
    }
}
