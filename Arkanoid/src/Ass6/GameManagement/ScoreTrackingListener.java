package Ass6.GameManagement;

import Ass6.Collision.Block;
import Ass6.Collision.HitListener;
import Ass6.Geometry.Ball;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
@Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}
