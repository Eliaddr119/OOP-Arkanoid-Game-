package Ass6.GameManagement;
import Ass6.Collision.Block;
import Ass6.Collision.Sprite;
import Ass6.Geometry.Velocity;
import Ass6.Geometry.Rectangle;
import Ass6.Geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(45, 5));
        list.add(Velocity.fromAngleAndSpeed(325, 5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(0x17561D));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.darkGray);
                d.fillRectangle(145, 180, 10, 240);
                d.fillRectangle(135, 410, 30, 40);
                d.setColor(Color.ORANGE);
                d.fillCircle(150, 180, 10);
                d.setColor(Color.RED);
                d.fillCircle(150, 180, 7);
                d.setColor(Color.BLACK);
                d.fillRectangle(102, 450, 100, 145);
                d.setColor(Color.WHITE);
                d.fillCircle(150, 180, 3);
                for (int j = 0; j < 5; j++) {
                    for (int i = 0; i < 5; i++) {
                        d.fillRectangle(110 + 18 * j, 460 + 30 * i, 10, 25);
                    }
                }
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
        List<Block> list = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            for (int i = 10 - j; i > 0; i--) {
                Color blockColor = null;
                if (j == 0) {
                    blockColor = Color.RED;
                }
                if (j == 1) {
                    blockColor = Color.GREEN;
                }
                if (j == 2) {
                    blockColor = Color.BLUE;
                }
                if (j == 3) {
                    blockColor = Color.YELLOW;
                }
                if (j == 4) {
                    blockColor = Color.MAGENTA;
                }
                Block newBlock = new Block(new Rectangle(new Point(785 - (50 * i), 150 + 30 * j),
                        50, 30, blockColor));
                list.add(newBlock);

            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
