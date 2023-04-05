import java.awt.Color;

/**
 * Ship class for the game piece ship.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 *
 */

public class Ship extends MovingObject {
    private static final double THRUST = .1;
    private static final double FRICTION = .99;
    private static final double TURNING = 0.1;
    private Vector2D currentThrust = new Vector2D(0, 0);

    /**
     * Ship constructor that takes a given pose and a radius of 10.
     * 
     * @param pose value
     */

    public Ship(Pose pose) {
        super(pose, 10);
    }

    /**
     * draws the ship if its not destroyed at its given pose and a width of 10
     * and a height 20.
     */

    public void draw() {
        if (!destroyed) {
            StdDraw.setPenColor(Color.WHITE);
            GameUtils.drawPoseAsTriangle(pose, 10, 20);
        }
    }

    /**
     * moves the ship bases on the current thrust and key inputs also sets up
     * the friction mechanic and does the wrapping logic.
     */

    public void move() {
        pose = pose.move(currentThrust);
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_DOWN)) {
            currentThrust = currentThrust.add(
                    new Vector2D(pose.getHeading(), THRUST));
        } else {

            currentThrust = currentThrust.newMagnitude(
                    currentThrust.getMagnitude() * FRICTION);
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_LEFT)) {
            pose = pose.newHeading(pose.getHeading() + TURNING);
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_RIGHT)) {
            pose = pose.newHeading(pose.getHeading() - TURNING);
        }
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
}
