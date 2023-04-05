/**
 * Static object class for all the non moving pieces.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 */

public abstract class StaticObject implements Playable {
    protected Point position;

    /**
     * gives each piece their position.
     * 
     * @param position value
     */

    public StaticObject(Point position) {
        this.position = position;
    }

    /**
     * gets the position.
     * 
     * @return position
     */

    public Point getPosition() {
        return this.position;
    }

}
