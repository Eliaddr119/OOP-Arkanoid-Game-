package Ass6.GameManagement;

import Ass6.Collision.Block;
import Ass6.Collision.Sprite;
import Ass6.Geometry.Point;
import Ass6.Geometry.Rectangle;
import Ass6.Geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(180, 3));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 50;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.GREEN);
                for (int i = 0; i <= 2; i++) {
                    d.drawCircle(d.getWidth() / 2, 225, 150 - 50 * i);
                }
                d.drawLine(225, 225,  575, 225);
                d.drawLine(400, 50, 400, 400);
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 30);
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
            }
        };
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(380, 205), 40, 40, Color.GREEN)));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
