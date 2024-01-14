/// Eliad Drori 211871439
package Ass6.Collision;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private final List<Sprite> spriteList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Gets sprite list.
     *
     * @return the sprite list
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}