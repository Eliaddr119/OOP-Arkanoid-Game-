/// Eliad Drori 211871439
package Ass6.Collision;
import Ass6.Geometry.Line;
import Ass6.Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private final List<Collidable> collidableList;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();

    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Gets colidable list.
     *
     * @return the colidable list
     */
    public List<Collidable> getColidableList() {
        return this.collidableList;
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory of the ball.
     * @return the closest collision to the ball.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo info = null; // Initiates the CollisionInfo.
        Double minDistance = null;
        for (Collidable collidable : collidableList) { // Checks every Colidable
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine((collidable.getCollisionRectangle()));
            if (collisionPoint == null) { // If there is no collision continue to the next iteration.
                continue;
            }
            if (minDistance == null) { // if the intersection is the first one found
                minDistance = collisionPoint.distance(trajectory.start());
                info = new CollisionInfo(collisionPoint, collidable);
                continue;
            }
            if (collisionPoint.distance(trajectory.start()) < minDistance) { //If the collision is the smallest
                minDistance = collisionPoint.distance(trajectory.start());
                info = new CollisionInfo(collisionPoint,  collidable);
            }
        }
        return info;
    }

}