//package lab2.Structures;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by ArtemBulkhak on 23/02/2019.
// */
//public class Point {
//
//    private int id;
//    private int x;
//    private int y;
//
//    private List<Angel> angels;
//
//    private Set<Triangle> triangles;
//
//
//    public Point(int x, int y){
//        this.x = x;
//        this.y = y;
//        angels = new ArrayList<>();
//
//        triangles = new HashSet<>();
//    }
//
//    public void addAngel(Angel angel){
//        angels.add(angel);
//    }
//
//    public float getSumAngels(){
//        float sumAngels = 0;
//        for (Angel angel: angels){
//            sumAngels += angel.getAngel();
//        }
//        return sumAngels;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public double getDistance(Point other) {
//        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
//    }
//
//    public static Point maxX(Point point, Point ... points) {
//        Point max = point;
//        for (Point p : points) {
//            if (p.x > max.x)
//                max = p;
//        }
//        return max;
//    }
//
//    public static Point minX(Point point, Point ... points) {
//        Point min = point;
//        for (Point p : points) {
//            if (p.x < min.x)
//                min = p;
//        }
//        return min;
//    }
//
//    public static Point maxY(Point point, Point ... points) {
//        Point max = point;
//        for (Point p : points) {
//            if (p.y > max.y)
//                max = p;
//        }
//        return max;
//    }
//
//    public static Point minY(Point point, Point ... points) {
//        Point min = point;
//        for (Point p : points) {
//            if (p.y < min.y)
//                min = p;
//        }
//        return min;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        Point point = (Point) o;
//        return this.x == point.x && this.y == point.y;
//    }
//
////    @Override
////    public String toString() {
////        return "Point{" +
////                "id=" + id +
////                ", x=" + x +
////                ", y=" + y +
////                '}';
////    }
//    @Override
//    public String toString() {
//        return x + "," + y;
//    }
//
//
//
//
//    public void addTriangle(Triangle triangle) {
//        triangles.add(triangle);
//    }
//
//    public Set<Triangle> getTriangles() {
//        return triangles;
//    }
//
//    public void removeTriangle(Triangle triangle) {
//        triangles.remove(triangle);
//    }
//}
