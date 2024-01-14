// Eliad Drori 211871439
package Ass6.Collision;
import Ass6.GameManagement.GameLevel;
import Ass6.Geometry.Ball;
import Ass6.Geometry.Point;
import Ass6.Geometry.Rectangle;
import Ass6.Geometry.Velocity;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}