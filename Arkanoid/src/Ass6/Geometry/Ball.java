/// Eliad Drori 211871439
package Ass6.Geometry;
import Ass6.Collision.Collidable;
import Ass6.Collision.CollisionInfo;
import Ass6.Collision.GameEnvironment;
import Ass6.Collision.Sprite;
import Ass6.GameManagement.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private final GameEnvironment gameEnvironment;

    // constructor


    /**
     * Instantiates a new Ball.
     *
     * @param x       the x
     * @param y       the y
     * @param r       the r
     * @param color   the color
     * @param gameEnv the game env
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnv) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnv;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param center  the center
     * @param r       the r
     * @param color   the color
     * @param gameEnv the game env
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnv) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnv;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return the x value of the ball
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y value of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the radios of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets center.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets color.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * Draws the ball of a Draw surface
     *
     * @param surface the surface
     */
    @Override
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * Sets velocity.
     * Sets the ball's velocity
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity using dx and dy values.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Moves the ball one step according to its current velocity, checking for any collisions
     * with other objects in the game environment.
     */
    public void moveOneStep() {
        // Create a line representing the ball's trajectory for the next step
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                2 * this.velocity.getDx() + this.center.getX(), 2 * this.velocity.getDy() + this.center.getY());

        // Check for collisions with other objects
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision == null) {
            // If no collisions, check if the ball collides with the paddle
            int paddleIndex = this.gameEnvironment.getColidableList().size() - 1;
            Collidable paddle = this.gameEnvironment.getColidableList().get(paddleIndex);
            Rectangle paddleRec = paddle.getCollisionRectangle();
            if (paddleRec.pointInRectangle(this.center, paddleRec)) {
                // If ball collides with paddle, bounce off the paddle
                setVelocity((-1) * this.getVelocity().getDx(), (-1) * this.getVelocity().getDy());
            }
            // Move the ball to its new position
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            // If there's a collision, determine the new velocity of the ball based on the object it collides with
            Collidable collisionObject = collision.collisionObject();
            Point collisionPoint = collision.collisionPoint();
            Velocity newVelocity = collisionObject.hit(this, collisionPoint, this.velocity);
            Rectangle collisionRec = collisionObject.getCollisionRectangle();
            if (collisionPoint.isOnLine(collisionPoint, collisionRec.getLeftVertical())) {
                // If ball collides with left side of object, move it to the left of the collision point
                this.center = new Point(collisionPoint.getX() - radius, collisionPoint.getY());
            }
            if (collisionPoint.isOnLine(collisionPoint, collisionRec.getRightVertical())) {
                // If ball collides with right side of object, move it to the right of the collision point
                this.center = new Point(collisionPoint.getX() + radius, collisionPoint.getY());
            }
            if (collisionPoint.isOnLine(collisionPoint, collisionRec.getUpperHorizontal())) {
                // If ball collides with top of object, move it above the collision point
                this.center = new Point(collisionPoint.getX(), collisionPoint.getY() - radius);
            }
            if (collisionPoint.isOnLine(collisionPoint, collisionRec.getLowerHorizontal())) {
                // If ball collides with bottom of object, move it below the collision point
                this.center = new Point(collisionPoint.getX(), collisionPoint.getY() + radius);
            }
            // Set the ball's new velocity
            setVelocity(newVelocity);
        }
    }

}
