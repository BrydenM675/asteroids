import java.awt.Color;

/**
 * Star class that sets up the 100 stars.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 *
 */

public class Star extends StaticObject {
    /**
     * star constructor that sets them at a given point.
     * 
     * @param position value
     */

    public Star(Point position) {
        super(position);
    }

    /**
     * update does nothing since they dont move.
     */

    public void update() {

    }

    /**
     * draws them at their random location.
     */

    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledCircle(position.getX(), position.getY(), 1);
    }

}
