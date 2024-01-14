/// Eliad Drori 211871439
package Ass6.Collision;
import Ass6.GameManagement.GameLevel;
import Ass6.Geometry.Ball;
import Ass6.Geometry.Line;
import Ass6.Geometry.Point;
import Ass6.Geometry.Rectangle;
import Ass6.Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private final GUI gui;
    private Line region1, region2, region3, region4, region5;
    private final int paddleSpeed;
    private static final int BORDERBLOCKWIDTH = 22;

    /**
     * Instantiates a new Paddle.
     * Creates 5 lines, each line for each region
     *
     * @param block       the block of the paddle
     * @param gui         the gui. the paddle needs the gui in order to get the keyboard sensor
     * @param paddleSpeed the paddle speed
     */
    public Paddle(Block block, GUI gui, int paddleSpeed) {
        this.paddle = block;
        this.gui = gui;
        keyboard = gui.getKeyboardSensor();
        this.paddleSpeed = paddleSpeed;
        Rectangle blockRec = block.getCollisionRectangle();
        Point recUpperLeft = blockRec.getUpperLeft();
        double regionLength = blockRec.getWidth() / 5;
        this.region1 = new Line(recUpperLeft, new Point(recUpperLeft.getX() + regionLength, recUpperLeft.getY()));
        this.region2 = new Line(this.region1.end(),
                new Point(recUpperLeft.getX() + 2 * regionLength, recUpperLeft.getY()));
        this.region3 = new Line(this.region2.end(),
                new Point(recUpperLeft.getX() + 3 * regionLength, recUpperLeft.getY()));
        this.region4 = new Line(this.region3.end(),
                new Point(recUpperLeft.getX() + 4 * regionLength, recUpperLeft.getY()));
        this.region5 = new Line(this.region4.end(),
                new Point(recUpperLeft.getX() + 5 * regionLength, recUpperLeft.getY()));
    }

    /**
     * Change regions.
     * Move the regions for their new position
     */
    void changeRegions() {
        Rectangle blockRec = paddle.getCollisionRectangle();
        Point recUpperLeft = blockRec.getUpperLeft();
        double regionLength = blockRec.getWidth() / 5;
        this.region1 = new Line(recUpperLeft, new Point(recUpperLeft.getX() + regionLength, recUpperLeft.getY()));
        this.region2 = new Line(this.region1.end(),
                new Point(recUpperLeft.getX() + 2 * regionLength, recUpperLeft.getY()));
        this.region3 = new Line(this.region2.end(),
                new Point(recUpperLeft.getX() + 3 * regionLength, recUpperLeft.getY()));
        this.region4 = new Line(this.region3.end(),
                new Point(recUpperLeft.getX() + 4 * regionLength, recUpperLeft.getY()));
        this.region5 = new Line(this.region4.end(),
                new Point(recUpperLeft.getX() + 5 * regionLength, recUpperLeft.getY()));
    }


    /**
     * Move left.
     * move the paddle to the left
     */
    public void moveLeft() {
        Rectangle paddleRec = paddle.getCollisionRectangle();
        Point newLeftUpper = new Point(paddleRec.getUpperLeft().getX() - paddleSpeed, paddleRec.getUpperLeft().getY());
        Rectangle newBlockRec = new Rectangle(newLeftUpper, paddleRec.getWidth(),
                paddleRec.getHeight(), paddleRec.getColor());
        this.paddle = new Block(newBlockRec);
    }

    /**
     * Move right.
     * Move the paddle to the right
     */
    public void moveRight() {
        Rectangle paddleRec = paddle.getCollisionRectangle();
        Point newLeftUpper = new Point(paddleRec.getUpperLeft().getX() + paddleSpeed, paddleRec.getUpperLeft().getY());
        Rectangle newBlockRec = new Rectangle(newLeftUpper, paddleRec.getWidth(),
                paddleRec.getHeight(), paddleRec.getColor());
        this.paddle = new Block(newBlockRec);
    }
    @Override
    public void timePassed() {
        int rightLimit = gui.getDrawSurface().getWidth() - BORDERBLOCKWIDTH;
        int recRightPoint = (int) paddle.getCollisionRectangle().getLowerRight().getX();
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && recRightPoint <= rightLimit) {
            moveRight();
        }
        int recLeftPoint = (int) paddle.getCollisionRectangle().getUpperLeft().getX();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && BORDERBLOCKWIDTH <= recLeftPoint) {
            moveLeft();
        }
        changeRegions();

    }
    @Override
    public void drawOn(DrawSurface d) {
        paddle.drawOn(d);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Calculate the speed of the ball based on its current velocity
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        // Check if the collision point is within the rectangle of the object being collided with
        if (getCollisionRectangle().pointInRectangle(collisionPoint, getCollisionRectangle())) {
            // If so, move the collision point slightly below the upper edge of the rectangle
            collisionPoint = new Point(collisionPoint.getX(), getCollisionRectangle().getUpperLeft().getY() + 1);
        } else if (collisionPoint.isOnLine(collisionPoint, getCollisionRectangle().getLowerHorizontal())) {
            // If the collision point is on the lower edge of the rectangle, move it to the upper edge
            collisionPoint = new Point(collisionPoint.getX(), getCollisionRectangle().getUpperLeft().getY());
        }

        // Check which region of the rectangle the collision occurred in, and return a new velocity vector based on that
        if (region1.isInXRange(region1, collisionPoint)) {
            return Velocity.fromAngleAndSpeed(-60, speed);
        } else if (region2.isInXRange(region2, collisionPoint)) {
            return Velocity.fromAngleAndSpeed(-30, speed);
        } else if (region3.isInXRange(region3, collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (region4.isInXRange(region4, collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (region5.isInXRange(region5, collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else if (getCollisionRectangle().getRightVertical().isInYRange(getCollisionRectangle().getRightVertical(),
                collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else if (getCollisionRectangle().getLeftVertical().isInYRange(getCollisionRectangle().getLeftVertical(),
                collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }

        // If no region was matched, return a reversed velocity vector
        return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
    }


    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}