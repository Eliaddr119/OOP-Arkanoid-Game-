/// Eliad Drori 211871439
package Ass6.Collision;
import Ass6.GameManagement.GameLevel;
import Ass6.Geometry.Ball;
import Ass6.Geometry.Point;
import Ass6.Geometry.Rectangle;
import Ass6.Geometry.Velocity;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier  {
private final Rectangle block;
private final List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     *
     * @param rec the rec
     */
    public Block(Rectangle rec) {
    this.block = rec;
    this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * getCollisiobRecrangle.
     *
     * @return the Rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
            this.block.drawRectangle(d);
    }

    /**
     *
     */
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return void.
     */
@Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.isOnLine(collisionPoint, block.getLeftVertical())) {
            this.notifyHit(hitter);
           return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }
        if (collisionPoint.isOnLine(collisionPoint, block.getRightVertical())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }
        if (collisionPoint.isOnLine(collisionPoint, block.getLowerHorizontal())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }
        if (collisionPoint.isOnLine(collisionPoint, block.getUpperHorizontal())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }
        return currentVelocity;
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
