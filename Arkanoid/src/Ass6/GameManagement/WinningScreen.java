package Ass6.GameManagement;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Winning screen.
 */
public class WinningScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private final Counter score;

    /**
     * Instantiates a new Winning screen.
     *
     * @param k     the k
     * @param score the score
     */
    public WinningScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.decode("#3cda31"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(50, 200, "You Won !", 150);
        d.drawText(150, 300, "Your Score is: " + score.getValue(), 64);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
