package Ass6.GameManagement;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.decode("#3cda31"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(225, 200, "Paused", 100);
        d.drawText(50, 300, "Press space to continue", 64);
        d.setColor(Color.WHITE);
        d.fillCircle(400, 450, 130);
        d.setColor(Color.BLACK);
        d.fillRectangle(335, 380, 40, 150);
        d.fillRectangle(425, 380, 40, 150);
    }
    @Override
    public boolean shouldStop() {
        return this.stop; }
}