// Eliad Drori 211871439
package Ass6.Collision;

import Ass6.Geometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point.
     *
     * @return the point of the collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;

    }
}