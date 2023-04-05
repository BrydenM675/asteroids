/**
 * Moving object that is the super class for all moving pieces that implements
 * Playable.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 *
 */

public abstract class MovingObject implements Playable {
    protected Pose pose;
    protected boolean destroyed;
    protected int points;
    protected double radius;

    /**
     * Moving Object constructor. All moving objects will have a pose and radius
     * for the hit box
     * 
     * @param pose value
     * @param radius value
     */

    public MovingObject(Pose pose, double radius) {
        this.pose = pose;
        this.radius = radius;

    }

    /**
     * if the object is not destroyed it calls the move method.
     */
    public void update() {
        if (!destroyed) {
            move();
        }
    }

    /**
     * abstract method move for all moving pieces.
     */

    public abstract void move();

    /**
     * destroyed method for all moving pieces to help with collisions.
     */

    public void destroy() {
        destroyed = true;
    }

    /**
     * the math for the hit boxes of all moving objects.
     * 
     * @param object value
     * @return a boolean if the radius of 2 objects are colliding with each
     *     other
     */

    public boolean collidedWith(MovingObject object) {
        double distance = Math.sqrt(
                Math.pow(object.pose.getX() - pose.getX(), 2)
                        + Math.pow(object.pose.getY() - pose.getY(), 2));

        if (distance <= object.radius || distance <= radius) {

            return true;
        }
        return false;
    }
}
