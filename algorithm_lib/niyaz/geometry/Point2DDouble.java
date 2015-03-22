package ru.ifmo.niyaz.geometry;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 06.01.13
 * Time: 0:31
 * To change this template use File | Settings | File Templates.
 */
public class Point2DDouble {
    public double x;
    public double y;

    public Point2DDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2DDouble subtract(Point2DDouble p) {
        return new Point2DDouble(x - p.x, y - p.y);
    }

    public Point2DDouble subtract(Point2DInteger p) {
        return new Point2DDouble(x - p.x, y - p.y);
    }

    public double vmul(Point2DDouble p) {
        return x * p.y - y * p.x;
    }

    public double vmul(Point2DInteger p) {
        return x * p.y - y * p.x;
    }

    public double smul(Point2DDouble p) {
        return x * p.x + y * p.y;
    }

    public double smul(Point2DInteger p) {
        return x * p.x + y * p.y;
    }


    public Point2DDouble add(Point2DDouble p) {
        return new Point2DDouble(x + p.x, y + p.y);
    }


    public double distanceSquared(Point2DDouble p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return dx * dx + dy * dy;
    }

    public double distance(Point2DDouble p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public String toString() {
        return "Point2DDouble{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Point2DDouble multiply(double d) {
        return new Point2DDouble(x * d, y * d);
    }

    public double squaredLength() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(squaredLength());
    }

    public double angle() {
        return Math.atan2(y, x);
    }
}
