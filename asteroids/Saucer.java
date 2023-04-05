import java.awt.Color;

/**
 * Sacuer class for the game piece Saucer.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 */

public class Saucer extends MovingObject {
    /**
     * Saucer constructor that gives it its pose and the point values.
     * 
     * @param pose value
     */

    public Saucer(Pose pose) {
        super(pose, 10);
        this.points = 400;
    }

    /**
     * draws a saucer if not destroyed.
     */

    public void draw() {
        if (!destroyed) {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.rectangle(pose.getX(), pose.getY(), 10, 5);
        }
    }

    /**
     * moves a saucer based on the given vector and also implements the randomly
     * moving mechanic and gives it its new heading also the wrapper logic.
     */

    public void move() {
        pose = pose.move(new Vector2D(pose.getHeading(), 2));
        int saucerLow = 1;
        int saucerHigh = 100;

        if (GameDriver.GENERATOR.nextInt(saucerHigh - saucerLow)
                + saucerLow <= 5) {
            double resultHeading = GameDriver.GENERATOR.nextDouble() * Math.PI
                    * 2;
            pose = pose.newHeading(resultHeading);

        }
        if (pose.getX() > 480 || pose.getY() > 480 || pose.getX() < 0
                || pose.getY() < 0) {
            destroy();

        }
    }

}
