/// Eliad Drori 211871439
package Ass6.Collision;
import Ass6.GameManagement.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    void removeFromGame(GameLevel gameLevel);
}