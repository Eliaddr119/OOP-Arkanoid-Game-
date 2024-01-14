/// Eliad Drori 211871439
package Ass6.Geometry;
import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private final Point start;
    private final Point end;
    private double m;
    private double b;
    /**
     * The Comparison threshold.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Instantiates a new Line.
     *
     * @param start the start of the line
     * @param end   the end of the line
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.getX() - end.getX() != 0) {
            this.m = (start.getY() - end.getY()) / (start.getX() - end.getX());
            this.b = start.getY() - this.m * start.getX();
        }
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x value of the first point
     * @param y1 the y value of the first point
     * @param x2 the x value of the second point
     * @param y2 the y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
        if (start.getX() - end.getX() != 0) {
            this.m = (start.getY() - end.getY()) / (start.getX() - end.getX());
            this.b = start.getY() - this.m * start.getX();
        }
    }

    /**
     * Double equals boolean.
     * Checks if two doubles are equal
     *
     * @param a the first double
     * @param b the second double
     * @return the boolean
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < Line.COMPARISON_THRESHOLD;
    }

    /**
     * Length double.
     *
     * @return the length of the line
     */
// Return the length of the line
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
// Returns the middle point of the line
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Is in x range boolean.
     * Checks if the point is within the x values of the line
     *
     * @param line  the line
     * @param point the point
     * @return the boolean
     */
    public boolean isInXRange(Line line, Point point) {
        return (line.start.getX() < point.getX() || doubleEquals(line.start.getX(), point.getX()))
                && (point.getX() < line.end().getX() || doubleEquals(point.getX(), line.end().getX()))
                || ((line.end.getX() < point.getX() || doubleEquals(line.end.getX(), point.getX()))
                && (point.getX() < line.start().getX() || doubleEquals(point.getX(), line.start().getX())));
    }

    /**
     * Is in y range boolean.
     * Checks if the point is within the y values of the line
     *
     * @param line  the line
     * @param point the point
     * @return the boolean
     */
    public boolean isInYRange(Line line, Point point) {
        return (line.start.getY() < point.getY() || doubleEquals(line.start.getY(), point.getY()))
                && (point.getY() < line.end().getY() || doubleEquals(point.getY(), line.end().getY()))
                || ((line.end.getY() < point.getY() || doubleEquals(line.end.getY(), point.getY()))
                && (point.getY() < line.start().getY() || doubleEquals(point.getY(), line.start().getY())));
    }

    /**
     * Is intersecting boolean.
     * Checks if 2 lines are intersecting with each other. The function checks if the lines are intersecting
     * for each scenario
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {

        // Check if both lines are vertical and parallel to each other
        if (doubleEquals(this.start().getX(), this.end().getX())
                && doubleEquals(other.start().getX(), other.end().getX())) {
            // Check if the lines overlap in the y-direction
            if (isInYRange(other, this.start()) || isInYRange(other, this.end())) {
                // If the lines overlap, they intersect
                return doubleEquals(other.start.getX(), this.start.getX());
            } else {
                // Check if any of the endpoints of the lines overlap
                return this.start().equals(other.end()) || this.start().equals(other.start())
                        || this.end.equals(other.end()) || this.end().equals(other.start());
            }
        } else if (doubleEquals(this.start().getX(), this.end().getX())) { // Check if this line is vertical
            // Calculate the intersection point with the other line
            Point inter = new Point(this.start.getX(), other.m * this.start.getX() + other.b);
            // Check if the intersection point lies within the x-range of the other line
            return (isInXRange(other, inter) && isInXRange(this, inter))
                    && (isInYRange(other, inter) && isInYRange(this, inter));
        } else if (doubleEquals(other.start().getX(), other.end().getX())) { // Check if the other line is vertical
            // Calculate the intersection point with this line
            Point inter = new Point(other.start.getX(), this.m * other.start.getX() + this.b);
            // Check if the intersection point lies within the x-range of this line
            return (isInXRange(other, inter) && isInXRange(this, inter))
                    && (isInYRange(other, inter) && isInYRange(this, inter));
        } else if (doubleEquals(other.m, this.m) && !doubleEquals(other.b, this.b)) {
            /* Check if the lines have the same slope but different y-intercepts */
            return false;
        } else if (doubleEquals(other.m, this.m) && doubleEquals(other.b, this.b)) {
            // Check if the lines have the same slope and y-intercept (they are overlapping)
            // Check if any of the endpoints of the lines overlap
            if (isInXRange(other, this.start()) || isInXRange(other, this.end())) {
                return true;
            } else {
                return this.start().equals(other.end()) || this.start().equals(other.start())
                        || this.end.equals(other.end()) || this.end().equals(other.start());
            }
        }
        // Calculate the intersection point between the two lines
        Point inter = new Point((other.b - this.b) / (this.m - other.m),
                this.m * ((other.b - this.b) / (this.m - other.m)) + this.b);
        // Check if the intersection point lies within the x-ranges of both lines
        return (isInXRange(other, inter) && isInXRange(this, inter))
                && (isInYRange(other, inter) && isInYRange(this, inter));
    }


    /**
     * Intersection with point.
     * returns the intersection point of 2 lines. If the lines do not intersect or have infinite intersection
     * pounts the function returns null.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        // Check if the lines are not intersecting
        if (!isIntersecting(other)) {
            return null;
        }
        // If the lines are vertical, they have no intersection point unless they are the same line
        if (doubleEquals(this.start().getX(), this.end().getX())
                && doubleEquals(other.start().getX(), other.end().getX())) {
            // If the lines are the same line, there is no intersection point
            if (this.equals(other)) {
                return null;
            } else if (this.start().equals(other.end())) {
                // If the lines have an endpoint in common, that point is the intersection point
                return new Point(this.start().getX(), this.start().getY());
            } else if (this.start().equals(other.start())) {
                return new Point(this.start().getX(), this.start().getY());
            } else if (this.end().equals(other.end())) {
                return new Point(this.end().getX(), this.end().getY());
            } else if (this.end().equals(other.start())) {
                return new Point(this.end().getX(), this.end().getY());
            } else if (isInYRange(other, this.start()) || isInYRange(other, this.end())) {
                // If the lines don't share any endpoints and don't overlap, there is no intersection point
                return null;
            }
        } else if (doubleEquals(this.start().getX(), this.end().getX())) {
            // If this line is vertical, calculate intersection point with other line
            Point inter = new Point(this.start.getX(), other.m * this.start.getX() + other.b);
            // If the intersection point is within the range of both lines, return it, otherwise return null
            if (isInXRange(other, inter)) {
                return inter;
            } else {
                return null;
            }
        } else if (doubleEquals(other.start().getX(), other.end().getX())) {
            // If the other line is vertical, calculate intersection point with this line
            Point inter = new Point(other.start.getX(), this.m * other.start.getX() + this.b);
            // If the intersection point is within the range of both lines, return it, otherwise return null
            if (isInXRange(this, inter)) {
                return inter;
            } else {
                return null;
            }
        } else if (doubleEquals(other.m, this.m) && !doubleEquals(other.b, this.b)) {
            // If the lines have the same slope but different y-intercepts, they don't intersect
            return null;
        } else if (doubleEquals(other.m, this.m) && doubleEquals(other.b, this.b)) {
            // If the lines have the same slope and y-intercept, they are the same line,
            // and there is no intersection point
            if (this.equals(other)) {
                return null;
            } else if (this.start().equals(other.end())) {
                return new Point(this.start().getX(), this.start().getY());
            } else if (this.start().equals(other.start())) {
                return new Point(this.start().getX(), this.start().getY());
            } else if (this.end().equals(other.end())) {
                return new Point(this.end().getX(), this.end().getY());
            } else if (this.end().equals(other.start())) {
                return new Point(this.end().getX(), this.end().getY());
            } else if ((isInXRange(other, this.start) && isInXRange(other, this.end))
                    || (isInXRange(this, other.end()) && isInXRange(this, other.end()))) {
                return null;
            }
            return null;
        }
        // Calculate intersection point of non-vertical lines
        Point inter = new Point((other.b - this.b) / (this.m - other.m),
                this.m * ((other.b - this.b) / (this.m - other.m)) + this.b);
        if (isInXRange(other, inter) && isInXRange(this, inter)) {
            return inter;
        }
        return null;
    }


    /**
     * Equals boolean.
     * Checks if two loines are equal to each other
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.equals(other.start()) && this.end.equals(other.end())) {
            return true;
        }
        return this.start.equals(other.end()) && this.end.equals(other.start());
    }


    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interList = rect.intersectionPoints(this);
        if (interList == null) {
            return null;
        } else {
            double minDistance = interList.get(0).distance(this.start);
            int index = 0;
            for (Point point : interList) {
                if (point.distance(this.start) < minDistance) {
                    minDistance = point.distance(this.start);
                    index = interList.indexOf(point);
                }
            }
            return interList.get(index);

        }
    }
}
