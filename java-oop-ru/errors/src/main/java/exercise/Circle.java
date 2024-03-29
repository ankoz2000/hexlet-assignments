package exercise;

// BEGIN
public class Circle {
    private final Point point;
    private final int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public int getRadius() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Invalid radius");
        }
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        return getRadius() * getRadius() * Math.PI;
    }
}
// END
