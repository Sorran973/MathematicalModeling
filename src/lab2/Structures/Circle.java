//package lab2.Structures;
//import lab2.Utils;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static lab2.Drawer.Drawer.RED;
//
//
//public class Circle {
//    private double[] center;
//    private double radius;
//    private double[] color;
//    private Triangle triangle;
//
//    public Circle(double[] center, double radius) {
//        this.center = center;
//        this.radius = radius;
//        this.color = RED;
//    }
//
//    public Circle(double[] center, double radius, Triangle triangle) {
//        this(center, radius);
//        this.triangle = triangle;
//    }
//
//    public Circle withColor(double[] color) {
//        this.color = color;
//        return this;
//    }
//
//    public double[] getCenter() {
//        return center;
//    }
//
//    public double getRadius() {
//        return radius;
//    }
//
//    public double[] getColor() {
//        return color;
//    }
//
//    public boolean doNotContainPoints(List<Point> points) {
////        Set<Point> points = triangles.stream()
////                .map(tr -> Arrays.asList(tr.getPoints()))
////                .flatMap(Collection::stream)
////                .collect(Collectors.toSet());
//
//        for (Point p : points) {
//            if (triangle.contains(p)) {
//                continue;
//            }
//
//            double distance = Utils.getDistance(p.getX(), p.getY(), center[0], center[1]);
//            if (Double.compare(distance, radius) == -1) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public boolean containsSomePointOfTriangle(Triangle triangle) {
//        return containsPoint(triangle.getFirstPoint()) || containsPoint(triangle.getSecondPoint()) || containsPoint(triangle.getThirdPoint());
//    }
//
//    public boolean containsPoint(Point p) {
//        if (triangle.contains(p)) {
//            return false;
//        }
//
//        return radius > distanceFromCenter(p);
//    }
//
//    private double distanceFromCenter(Point p) {
//        return Utils.getDistance(center[0], center[1], p.getX(), p.getY());
//    }
//
//    public static Circle getCircumcircle(Triangle triangle) {
//        double[] center = Utils.getCircumcenter(triangle);
//        double radius = Utils.getDistance(center[0], center[1], triangle.getFirstPoint().getX(), triangle.getFirstPoint().getY());
//
//        return new Circle(center, radius, triangle);
//    }
//
//    public void setColor(double[] color) {
//        this.color = color;
//    }
//
//    public List<Point> filterPointsInside(List<Point> points) {
//        return points
//                .stream()
//                .filter(p -> radius > Utils.getDistance(center[0], center[1], p.getX(), p.getY()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//
//        if (obj == null || this.getClass() != obj.getClass()) {
//            return false;
//        }
//
//        Circle other = (Circle) obj;
//        return Arrays.equals(this.center, other.center) && this.radius == other.radius;
//    }
//
//    @Override
//    public String toString() {
//        return "Circle{" +
//                "center=" + Arrays.toString(center) +
//                ", radius=" + radius +
//                ", triangle=" + triangle +
//                '}';
//    }
//}