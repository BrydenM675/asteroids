import java.awt.Color;
import java.util.ArrayList;

/**
 * The AsteroidGame class that runs the game through Playable.
 * 
 * @author Bryden Mollenauer
 * @version 4/12/21
 */

public class AsteroidsGame implements Playable {
    private Star[] stars;
    private Asteroid[] asteroids;
    private ArrayList<Saucer> saucers;
    private Ship ship;
    private ArrayList<Bullet> bullets;
    private Scoreboard scoreboard;
    private Lives lives;

    /**
     * Constructor for AsteroidGame. Initializes all the game pieces needed to
     * play the game.
     */

    public AsteroidsGame() {
        this.stars = new Star[100];
        int low = 0;
        int high = 480;
        for (int i = 0; i < 100; i++) {

            int resultX = GameDriver.GENERATOR.nextInt(high - low) + low;
            int resultY = GameDriver.GENERATOR.nextInt(high - low) + low;
            stars[i] = new Star(new Point(resultX, resultY));

        }
        this.asteroids = new Asteroid[10];
        for (int i = 0; i < 10; i++) {
            int lowSize = 0;
            int highSize = 100;
            int resultSize = GameDriver.GENERATOR.nextInt(highSize - lowSize)
                    + lowSize;
            int resultX = GameDriver.GENERATOR.nextInt(high - low) + low;
            int resultY = GameDriver.GENERATOR.nextInt(high - low) + low;
            double resultHeading = GameDriver.GENERATOR.nextDouble() * Math.PI
                    * 2;
            Asteroid.AsteroidSize pickSize;
            if (resultSize < 50) {
                pickSize = Asteroid.AsteroidSize.SMALL;
            } else if (resultSize < 75) {
                pickSize = Asteroid.AsteroidSize.MEDIUM;
            } else {
                pickSize = Asteroid.AsteroidSize.LARGE;
            }
            asteroids[i] = new Asteroid(
                    new Pose(resultX, resultY, resultHeading), pickSize);

        }
        this.saucers = new ArrayList<Saucer>();
        this.ship = new Ship(new Pose(240, 240, Math.PI * .5));
        this.bullets = new ArrayList<Bullet>();
        this.scoreboard = new Scoreboard(new Point(60, 460));
        this.lives = new Lives(new Point(60, 420));
    }

    /**
     * Updates all the pieces in the game by calling their specific update
     * methods.
     */

    public void update() {
        if (lives.getLives() == 0) {
            return;
        }
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i].update();
        }
        int saucerLow = 1;
        int saucerHigh = 1000;
        int low = 0;
        int high = 480;
        if (GameDriver.GENERATOR.nextInt(saucerHigh - saucerLow)
                + saucerLow <= 2) {
            boolean saucerFound = false;
            int resultX = GameDriver.GENERATOR.nextInt(high - low) + low;
            int resultY = GameDriver.GENERATOR.nextInt(high - low) + low;
            double resultHeading = GameDriver.GENERATOR.nextDouble() * Math.PI
                    * 2;
            for (int i = 0; i < saucers.size(); i++) {
                if (saucers.get(i).destroyed) {
                    saucers.set(i, new Saucer(
                            new Pose(resultX, resultY, resultHeading)));
                    saucerFound = true;
                    break;
                }
            }
            if (!saucerFound) {
                saucers.add(
                        new Saucer(new Pose(resultX, resultY, resultHeading)));
            }
        }
        for (int i = 0; i < saucers.size(); i++) {
            saucers.get(i).update();
        }
        ship.update();
        if (StdDraw.hasNextKeyTyped()
                && StdDraw.nextKeyTyped() == java.awt.event.KeyEvent.VK_SPACE) {
            boolean bulletFound = false;

            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).destroyed) {
                    bullets.set(i, new Bullet(ship.pose));
                    bulletFound = true;
                    break;
                }
            }
            if (!bulletFound) {
                bullets.add(new Bullet(ship.pose));
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).destroyed) {
                continue;
            }
            for (int j = 0; j < asteroids.length; j++) {
                if (asteroids[j].destroyed) {
                    continue;
                }
                if (bullets.get(i).collidedWith(asteroids[j])) {
                    asteroids[j].destroy();
                    scoreboard.increasePoints(asteroids[j].points);
                }
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).destroyed) {
                continue;
            }
            for (int j = 0; j < saucers.size(); j++) {
                if (saucers.get(j).destroyed) {
                    continue;
                }
                if (bullets.get(i).collidedWith(saucers.get(j))) {
                    saucers.get(j).destroy();
                    scoreboard.increasePoints(saucers.get(j).points);
                }
            }
        }
        for (int j = 0; j < saucers.size(); j++) {
            if (saucers.get(j).destroyed) {
                continue;
            }
            if (ship.collidedWith(saucers.get(j))) {
                ship.destroy();
                saucers.get(j).destroy();
                scoreboard.increasePoints(saucers.get(j).points);
                lives.removeLife();
                if (lives.getLives() > 0) {
                    ship = new Ship(new Pose(240, 240, Math.PI * .5));
                }
            }
        }
        for (int j = 0; j < asteroids.length; j++) {
            if (asteroids[j].destroyed) {
                continue;
            }
            if (ship.collidedWith(asteroids[j])) {
                ship.destroy();
                asteroids[j].destroy();
                scoreboard.increasePoints(asteroids[j].points);
                lives.removeLife();
                if (lives.getLives() > 0) {
                    ship = new Ship(new Pose(240, 240, Math.PI * .5));
                }
            }
        }
        boolean asteroidsAlive = false;
        boolean saucersAlive = false;
        for (int i = 0; i < asteroids.length; i++) {
            if (!asteroids[i].destroyed) {
                asteroidsAlive = true;
                break;
            }
        }
        for (int i = 0; i < saucers.size(); i++) {
            if (!saucers.get(i).destroyed) {
                saucersAlive = true;
                break;
            }
        }
        if (!asteroidsAlive && !saucersAlive) {
            for (int i = 0; i < 10; i++) {
                int lowSize = 0;
                int highSize = 100;
                int resultSize = GameDriver.GENERATOR.nextInt(
                        highSize - lowSize) + lowSize;
                int resultX = GameDriver.GENERATOR.nextInt(high - low) + low;
                int resultY = GameDriver.GENERATOR.nextInt(high - low) + low;
                double resultHeading = GameDriver.GENERATOR.nextDouble()
                        * Math.PI * 2;
                Asteroid.AsteroidSize pickSize;
                if (resultSize < 50) {
                    pickSize = Asteroid.AsteroidSize.SMALL;
                } else if (resultSize < 75) {
                    pickSize = Asteroid.AsteroidSize.MEDIUM;
                } else {
                    pickSize = Asteroid.AsteroidSize.LARGE;
                }
                asteroids[i] = new Asteroid(
                        new Pose(resultX, resultY, resultHeading), pickSize);
            }
        }
    }

    /**
     * draws all game pieces by calling their methods.
     */

    public void draw() {
        StdDraw.clear(Color.BLACK);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw();
        }
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i].draw();
        }
        for (int i = 0; i < saucers.size(); i++) {
            saucers.get(i).draw();
        }
        ship.draw();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw();
        }
        scoreboard.draw();
        lives.draw();
    }

}
