/// Eliad Drori 211871439
package Ass6.GameManagement;
import Ass6.Collision.BallRemover;
import Ass6.Collision.Block;
import Ass6.Collision.BlockRemover;
import Ass6.Collision.Collidable;
import Ass6.Collision.GameEnvironment;
import Ass6.Collision.Paddle;
import Ass6.Collision.Sprite;
import Ass6.Collision.SpriteCollection;
import Ass6.Geometry.Ball;
import Ass6.Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Ass6.Geometry.Rectangle;
import Ass6.Geometry.Point;
import biuoop.KeyboardSensor;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final List<Sprite> toBeDeleted = new ArrayList<>();
    private final GUI gui;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final Counter scoreCounter;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInfo;

    /**
     * Instantiates a new Game.
     *
     * @param levelInfo    the level info
     * @param ar           the ar
     * @param k            the k
     * @param scoreCounter the score counter
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor k, Counter scoreCounter) {
        this.levelInfo = levelInfo;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(levelInfo.numberOfBlocksToRemove());
        this.ballCounter = new Counter(levelInfo.numberOfBalls());
        this.scoreCounter = scoreCounter;
        this.keyboard = k;
        this.runner = ar;
        this.gui = ar.getGui();
    }

    /**
     * Add to be deleted.
     *
     * @param s the s
     */
    public void addToBeDeleted(Sprite s) {
        toBeDeleted.add(s);
    }

    /**
     * Multi delete.
     */
    public void multiDelete() {
        for (Sprite s : toBeDeleted) {
           s.removeFromGame(this);
        }
        toBeDeleted.clear();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (Velocity v : levelInfo.initialBallVelocities()) {
            Ball ball1 = new Ball(new Point((int) (gui.getDrawSurface().getWidth() / 2),
                    550), 6, Color.WHITE, environment);
            ball1.setVelocity(v);
            ball1.addToGame(this);
        }
    }

    /**
     * Initialize blocks.
     *
     * @param blockRemover the block remover
     * @param score        the score
     */
    public void initializeBlocks(BlockRemover blockRemover, ScoreTrackingListener score) {
        for (Block b : levelInfo.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(score);
            b.addToGame(this);
        }
        Block b1 = new Block(new Rectangle(new Point(0, 30), 800, 20, Color.DARK_GRAY));
        Block b2 = new Block(new Rectangle(new Point(0, 50), 20, 580, Color.DARK_GRAY));
        Block b4 = new Block(new Rectangle(new Point(780, 50), 20, 580, Color.DARK_GRAY));
        Block deathBlock = new Block(new Rectangle(new Point(20, 595), 760, 5, Color.DARK_GRAY));
        b1.addToGame(this);
        b2.addToGame(this);
        b4.addToGame(this);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(new BallRemover(this, ballCounter));
    }


    /**
     * Initialize.
     */
    public void initialize() {
        levelInfo.getBackground().addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, levelInfo.levelName());
        initializeBlocks(blockRemover, scoreListener);
        Block paddleBlock = new Block(new Rectangle(new Point((int) (400 - levelInfo.paddleWidth() / 2),
                560), levelInfo.paddleWidth(),
                20, Color.GREEN));
        Paddle paddle = new Paddle(paddleBlock, this.gui, levelInfo.paddleSpeed());
        paddle.addToGame(this);
        createBallsOnTopOfPaddle();
        scoreIndicator.addToGame(this);
    }

    /**
     * Run.
     * Run the game
     */
// Run the game -- start the animation loop.
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        multiDelete();
        if (ballCounter.getValue() == 0) {
            this.running = false;
        }
        if (blockCounter.getValue() <= 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            KeyPressStoppableAnimation ksa = new KeyPressStoppableAnimation(keyboard,
                    keyboard.SPACE_KEY,
                    new PauseScreen(keyboard));
            runner.run(ksa);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Balls remaining Balls.
     *
     * @return the balls
     */
    public int ballsRemaining() {
        return ballCounter.getValue();
    }

    /**
     * Blocks remaining blocks.
     *
     * @return the blocks
     */
    public int blocksRemaining() {
        return blockCounter.getValue();
    }

}