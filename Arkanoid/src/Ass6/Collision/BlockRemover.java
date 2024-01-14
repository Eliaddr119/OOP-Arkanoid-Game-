package Ass6.Collision;

import Ass6.GameManagement.Counter;
import Ass6.GameManagement.GameLevel;
import Ass6.Geometry.Ball;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel       the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        gameLevel.addToBeDeleted(beingHit);
    }
}
