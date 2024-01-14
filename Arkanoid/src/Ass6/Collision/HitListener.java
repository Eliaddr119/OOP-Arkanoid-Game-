package Ass6.Collision;

import Ass6.Geometry.Ball;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
