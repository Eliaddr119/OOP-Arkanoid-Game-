package Ass6.GameManagement;

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private final Counter scoreCounter;
    private final GUI gui;
    private final AnimationRunner ar;
    private final KeyboardSensor keyboardSensor;


    /**
     * Instantiates a new Game flow.
     */
    public GameFlow() {
        this.scoreCounter = new Counter(0);
        this.gui = new GUI("Arknoid", 800, 600);
        this.ar = new AnimationRunner(gui, 60);
        this.keyboardSensor = gui.getKeyboardSensor();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, ar, keyboardSensor, scoreCounter);
            level.initialize();

            while (level.ballsRemaining() > 0 && level.blocksRemaining() > 0) {
                level.run();
            }

            if (level.ballsRemaining() <= 0) {
                KeyPressStoppableAnimation ksa = new KeyPressStoppableAnimation(keyboardSensor,
                        keyboardSensor.SPACE_KEY,
                        new LosingScreen(keyboardSensor, scoreCounter));
                ar.run(ksa);
                gui.close();
            }

        }
            KeyPressStoppableAnimation ksa = new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                    new WinningScreen(keyboardSensor, scoreCounter));
            ar.run(ksa);
            gui.close();

    }
}

