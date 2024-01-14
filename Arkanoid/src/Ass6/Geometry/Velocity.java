/// Eliad Drori 211871439
package Ass6.Geometry;

/**
 * The type Velocity.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * From angle and speed velocity.
     * Creates new velocity object using angle and speed values.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -(speed * Math.cos(radians));
        return new Velocity(dx, dy);
    }

    /**
     * Apply to point.
     * Apply velocity to a point and returns a new center point for the circle.
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + getDx(), p.getY() + getDy());
    }
}