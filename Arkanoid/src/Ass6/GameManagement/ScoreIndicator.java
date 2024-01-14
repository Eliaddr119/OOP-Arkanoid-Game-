package Ass6.GameManagement;

import Ass6.Collision.Sprite;
import biuoop.DrawSurface;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final String levelName;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score     the score
     * @param levelName the level name
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.score = score;
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 25, "Score: " + score.getValue(), 20);
        d.drawText(500, 25, "Level Name: " + levelName, 20);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);

    }
}
