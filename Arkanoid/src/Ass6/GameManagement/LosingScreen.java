package Ass6.GameManagement;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Losing screen.
 */
public class LosingScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final Counter score;

    /**
     * Instantiates a new Losing screen.
     *
     * @param k     the k
     * @param score the score
     */
    public LosingScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(50, 200, "You Lose !", 150);
        d.drawText(125, 300, "Your Score is: " + score.getValue(), 64);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}


