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
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(Velocity.fromAngleAndSpeed(270 - 25 * i, 5));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(0x83B4EA));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 30);
                d.setColor(new Color(202, 200, 148));
                d.fillCircle(350, 130, 80);
                for (int i = 1; i <= 100; i++) {
                    d.drawLine(350, 130, 8 * i, 250);
                }
                d.setColor(new Color(195, 195, 70));
                d.fillCircle(350, 130, 70);
                d.setColor(new Color(255, 216, 45));
                d.fillCircle(340, 120, 60);
                d.setColor(Color.BLACK);
                d.fillCircle(315, 110, 8);
                d.fillCircle(365, 110, 8);
                d.fillOval(315, 140, 50, 25);
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
        List<Block> list = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Color color = null;
            if (i == 1 || i == 2) {
                color = Color.RED;
            }
            if (i == 3 || i == 4) {
                color = Color.ORANGE;
            }
            if (i == 5 || i == 6) {
                color = Color.YELLOW;
            }
            if (i == 7 || i == 8 || i == 9) {
                color = Color.GREEN;
            }
            if (i == 10 || i == 11) {
                color = Color.BLUE;
            }
            if (i == 12 || i == 13) {
                color = Color.PINK;
            }
            if (i == 14 || i == 15) {
                color = Color.CYAN;
            }
            Block b = new Block(new Rectangle(new Point(15 + 51.333 * (i - 1), 250),
                    51.3333, 20, color));
            list.add(b);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
