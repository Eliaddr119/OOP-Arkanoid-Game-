/// Eliad Drori 211871439
package Ass6.Geometry;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {

    private final Line leftVertical;
    private final Line upperHorizontal;
    private final Line rightVertical;
    private final Line lowerHorizontal;
    private final double height;
    private final double width;
    private final Point upperLeft;
    private final Line[] lines = new Line[4];
    private final Color color;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.leftVertical = new Line(upperLeft, lowerLeft);
        lines[0] = leftVertical;
        this.lowerHorizontal = new Line(lowerLeft, lowerRight);
        lines[1] = lowerHorizontal;
        this.rightVertical = new Line(lowerRight, upperRight);
        lines[2] = rightVertical;
        this.upperHorizontal = new Line(upperLeft, upperRight);
        lines[3] = upperHorizontal;
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.color = color;
    }

    /**
     * DrawRectangle.
     * Draw the rectangle on the drawsurface.
     *
     * @param d the DrawSurface
     */
    public void drawRectangle(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }


    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> interList = new ArrayList<>();
        for (Line eachLine : lines) { //Check if the line intersect with each line from the rectangle
            if (eachLine.intersectionWith(line) != null) {
                interList.add(eachLine.intersectionWith(line));
            }
        }
        if (interList.isEmpty()) { // if no intersections are found
            return null;
        }
        return interList;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
            return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left Point of the rectangle
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets lower right.
     *
     * @return the lower right Point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * Gets upper right.
     *
     * @return the upper right Point of the rectangle
     */
    public Point getUpperRight() {
       return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * Gets left vertical.
     *
     * @return the left vertical Line of the rectangle
     */
    public Line getLeftVertical() {
        return this.leftVertical;
    }

    /**
     * Gets lower horizontal.
     *
     * @return the lower horizontal Line of the rectangle
     */
    public Line getLowerHorizontal() {
        return this.lowerHorizontal;
    }

    /**
     * Gets right vertical.
     *
     * @return the right vertical Line of the rectangle
     */
    public Line getRightVertical() {
        return this.rightVertical;
    }

    /**
     * Gets upper horizontal.
     *
     * @return the upper horizontal Line of the rectangle
     */
    public Line getUpperHorizontal() {
        return this.upperHorizontal;
    }

    /**
     * Gets color.
     * returns the Color of the rectangle
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Point in rectangle boolean.
     *
     * @param point the point that we want to check whether is in the rectangle
     * @param rec   the rec
     * @return the boolean
     */
    public boolean pointInRectangle(Point point, Rectangle rec) {
        if (rec.getUpperLeft().getX() < point.getX() && rec.getUpperRight().getX() > point.getX()) {
            return rec.getLowerRight().getY() > point.getX() && rec.getUpperRight().getY() < point.getY();
        }
        return false;
    }
}