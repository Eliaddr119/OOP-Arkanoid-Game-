package Ass6.Collision;

import Ass6.GameManagement.Counter;
import Ass6.GameManagement.GameLevel;
import Ass6.Geometry.Ball;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param g              the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel g, Counter remainingBalls) {
        this.gameLevel = g;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        gameLevel.addToBeDeleted(hitter);

    }
}
