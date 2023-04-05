/**
 * ScoreBoard class.
 * @author Bryden Mollenauer.
 * @version 4/12/21
 * 
 */

public class Scoreboard extends StaticObject {

    private int points;
    /**
     * ScoreBoard Constructor that gives it its position.
     * @param position value
     */
    
    public Scoreboard(Point position) {
        super(position);
        this.points = 0;
    }
    /**
     * update does nothing since it does not move.
     */
    
    public void update() {
        
    }
    /**
     * draws the score board at its position and prints 
     * the score.
     */
    
    public void draw() {
        StdDraw.text(position.getX(), position.getY(),
                String.format("Score: %d", points));
    }
    /**
     * increases point by taking the points of the object you destroyed.
     * @param points value 
     */
    
    public void increasePoints(int points) {
        this.points += points;
        
    }
    

}
