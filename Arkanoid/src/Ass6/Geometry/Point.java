/// Eliad Drori 211871439
package Ass6.Geometry;

/**
 * The type Point.
 */
public class Point {
    private final double  x;
    private final double y;
    /**
     * The Comparison threshold.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * returns the distance between 2 points
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    /**
     * Double equals boolean.
     * checks if 2 doubles are equal.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < Line.COMPARISON_THRESHOLD;
    }

    /**
     * Equals boolean.
     * Checks if two points are equal to each other
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return doubleEquals(this.x, other.getX()) &&  (doubleEquals(this.y, other.getY()));
    }

    /**
     * Gets x.
     *
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Is on line boolean.
     *
     * @param point the point
     * @param line  the line
     * @return the boolean
     */
    public boolean isOnLine(Point point, Line line) {
        return line.isInXRange(line, point) && line.isInYRange(line, point);
    }

}