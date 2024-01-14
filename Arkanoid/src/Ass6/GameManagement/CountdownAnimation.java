package Ass6.GameManagement;

import Ass6.Collision.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean run;
    private long time;
    private long start;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.time = (long) (1000 * numOfSeconds / (double) countFrom);
        this.start = System.currentTimeMillis();
        this.run = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        if (System.currentTimeMillis() - start < time) {
            d.setColor(Color.GREEN);
            d.fillRectangle(305, 150, 175, 225);
            d.setColor(Color.BLACK);
            d.drawText(325, 350, Integer.toString(countFrom), 250);
        } else {
        countFrom -= 1;
        this.start = System.currentTimeMillis();
        if (countFrom <= 0) {
            this.run = false;
        }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.run;
    }
}
