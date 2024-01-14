package Ass6.Collision;

import Ass6.Geometry.Ball;

/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * Instantiates a new Printing hit listener.
     */
    public PrintingHitListener() {
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
