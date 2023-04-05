import java.awt.Color;

/**
 * Asteroid class that extends Moving object.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 *
 */
public class Asteroid extends MovingObject {
    /**
     * Enum that determines the radius and point values for each Asteroid.
     * 
     * @author Bryden Mollenauer
     * @version 4/12/21
     */

    public enum AsteroidSize {
        SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);

        private int radius;
        private int points;

        /**
         * Constructor for the enum AsteroidSize.
         * 
         * @param radius value
         * @param points value
         */
        AsteroidSize(int radius, int points) {
            this.radius = radius;
            this.points = points;
        }

    }

    private AsteroidSize size;

    /**
     * Standard Asteroid constructor.
     * 
     * @param pose value
     * @param size value
     */

    public Asteroid(Pose pose, AsteroidSize size) {
        super(pose, size.radius);
        this.size = size;
        this.points = size.points;

    }

    /**
     * if the asteroid is not destroyed it will draw at the given x and y.
     */

    public void draw() {
        if (!destroyed) {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.circle(pose.getX(), pose.getY(), size.radius);
        }
    }

    /**
     * moves with the given vector heading and does the wrap logic.
     */

    public void move() {
        pose = pose.move(new Vector2D(pose.getHeading(), 1));
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
