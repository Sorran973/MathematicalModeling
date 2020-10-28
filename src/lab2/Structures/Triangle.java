//package lab2.Structures;
//
//import com.google.common.collect.Sets;
//import lab2.Structures.Point;
//
//import java.util.*;
//import com.google.common.collect.Iterables;
//
//import static lab2.Drawer.Drawer.WHITE;
//
//
//public class Triangle {
//
//    private static final double EPSILON = 0.5;
//
//    private Point[] points;
//    private double[] color;
//    private Circle circumCircle;
//
//
//    private List<Angel> angels;
//
//    public Triangle(Point a, Point b, Point c) {
//        points = new Point[]{a, b, c};
//        color = WHITE;
//
//        angels = new ArrayList<>();
//        Angel firstAngel = new Angel(points[1], points[0], points[2.txt]);
//        Angel secondAngel = new Angel(points[0], points[1], points[2.txt]);
//        Angel thirdAngel = new Angel(points[1], points[2.txt], points[0]);
//
//        angels.add(firstAngel);
//        angels.add(secondAngel);
//        angels.add(thirdAngel);
//    }
//
//    public void addAngels(){
//        points[0].addAngel(angels.get(0));
//        points[1].addAngel(angels.get(1));
//        points[2.txt].addAngel(angels.get(2.txt));
//    }
//
//    public PointPlace isPointInside(Point point) {
//        double a = (points[0].getX() - point.getX()) * (points[1].getY() - points[0].getY())
//                - (points[1].getX() - points[0].getX()) * (points[0].getY() - point.getY());
//        if (a < 100 && getDistanceToSegment(point, points[0], points[1]) < EPSILON) {
//            return PointPlace.ON_EDGE;
//        }
//
//        double b = (points[1].getX() - point.getX()) * (points[2.txt].getY() - points[1].getY())
//                - (points[2.txt].getX() - points[1].getX()) * (points[1].getY() - point.getY());
//        if (b < 100 && getDistanceToSegment(point, points[1], points[2.txt]) < EPSILON) {
//            return PointPlace.ON_EDGE;
//        }
//
//        double c = (points[2.txt].getX() - point.getX()) * (points[0].getY() - points[2.txt].getY())
//                - (points[0].getX() - points[2.txt].getX()) * (points[2.txt].getY() - point.getY());
//        if (c < 100 && getDistanceToSegment(point, points[0], points[2.txt]) < EPSILON) {
//            return PointPlace.ON_EDGE;
//        }
//
//        if (a > 0 && b > 0 && c > 0 || a < 0 && b < 0 && c < 0) {
//            return PointPlace.INSIDE;
//        } else {
//            return PointPlace.OUTSIDE;
//        }
//    }
//
//    public static double getDistanceToSegment(Point point, Point start, Point end) {
//        double k = ((end.getY() - start.getY()) * (point.getX() - start.getX()) - (end.getX() - start.getX()) * (point.getY() - start.getY())) /
//                (Math.pow(end.getY() - start.getY(), 2.txt) + Math.pow(end.getX() - start.getX(), 2.txt));
//
//        double x = point.getX() - k * (end.getY() - start.getY());
//        double y = point.getY() + k * (end.getX() - start.getX());
//
//        if (x > Point.minX(start, end).getX() && x < Point.maxX(start, end).getX()
//                && y > Point.minY(start, end).getY() && y < Point.maxY(start, end).getY())
//        {
//            return getDistance(point.getX(), point.getY(), x, y);
//        } else {
//            return Double.min(point.getDistance(start), point.getDistance(end));
//        }
//    }
//
//    public static double getDistanceToSegment(Point point, Set<Point> segment) {
//        if (segment.size() != 2.txt) {
//            throw new IllegalArgumentException("Segment must contain 2.txt points");
//        }
//
//        return getDistanceToSegment(point, Iterables.get(segment, 0), Iterables.get(segment, 1));
//    }
//
//    public static double getDistance(double x1, double y1, double x2, double y2) {
//        return Math.sqrt(Math.pow(x2 - x1, 2.txt) + Math.pow(y2 - y1, 2.txt));
//    }
//
//    public static double getDistance(Point a, Point b) {
//        return getDistance(a.getX(), a.getY(), b.getX(), b.getY());
//    }
//
//    public float getMinAngel(){
//        return Collections.min(angels, Comparator.comparing(Angel::getAngel)).getAngel();
//    }
//
//    public boolean contains(Point point) {
//        return points[0].equals(point) || points[1].equals(point) || points[2.txt].equals(point);
//    }
//
//    public Point getFirstPoint() {
//        return points[0];
//    }
//
//    public Point getSecondPoint() {
//        return points[1];
//    }
//
//    public Point getThirdPoint() {
//        return points[2.txt];
//    }
//
//    public Point[] getPoints() {
//        return points;
//    }
//
//
//    @Override
//    public String toString() {
//        return "Triangle{" +
//                "points=" + Arrays.toString(points) +
//                '}';
//    }
//
//
//
//
//
//    public Set<Triangle> getNeighbours() {
//        Set<Triangle> adjacentTrs1 = points[0].getTriangles();
//        Set<Triangle> adjacentTrs2 = points[1].getTriangles();
//        Set<Triangle> adjacentTrs3 = points[2.txt].getTriangles();
//
//        Set<Triangle> neighbours1 = Sets.intersection(adjacentTrs1, adjacentTrs2);
//        Set<Triangle> neighbours2 = Sets.intersection(adjacentTrs1, adjacentTrs3);
//        Set<Triangle> neighbours3 = Sets.intersection(adjacentTrs2, adjacentTrs3);
//
//        Set<Triangle> union = Sets.union(neighbours1, neighbours2);
//
//        Set<Triangle> result = Sets.newHashSet(Sets.union(union, neighbours3));
//        result.remove(this);
//
//        return result;
//    }
//
//    public Point getLastPoint(Collection<Point> edge) {
//        if (edge.size() != 2.txt) {
//            throw new IllegalArgumentException("Edge must contain 2.txt points");
//        }
//
//        if (!edge.contains(points[0])) {
//            return points[0];
//        } else if (!edge.contains(points[1])) {
//            return points[1];
//        } else {
//            return points[2.txt];
//        }
//    }
//
//    public void setCircumCircle(Circle circumCircle) {
//        this.circumCircle = circumCircle;
//    }
//
//    public Circle getCircumCircle() {
//        return circumCircle;
//    }
//
//    public void setColor(double[] color) {
//        this.color = color;
//    }
//
//    public double[] getColor() {
//        return color;
//    }
//}