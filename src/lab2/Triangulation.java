//package lab2;
//
///**
// * Created by ArtemBulkhak on 03/04/2019.
// */
//
//import com.google.common.collect.Iterables;
//import lab2.Structures.Circle;
//import lab2.Structures.Point;
//import lab2.Structures.PointPlace;
//import lab2.Structures.Triangle;
//
//import java.util.*;
//
//import static lab2.Drawer.Drawer.GRAY;
//import static lab2.Drawer.Drawer.RED;
//import static lab2.Drawer.Drawer.WHITE;
//import static lab2.Utils.doIntersect;
//
//
//public class Triangulation {
//
//    private List<Point> points;
//    private List<Point> boundaryPoint;
//    private List<Triangle> triangles;
//    private List<Point> curPoints;
//
//    private Set<Triangle> trianglesSet;
//    private Stack<Triangle> trianglesStack;
//    private Set<Circle> circles;
//
//    public Triangulation(List<Point> points) {
//        this.points = points;
//        this.boundaryPoint = new ArrayList<>();
//        this.curPoints = new ArrayList<>(3);
//        this.triangles = new ArrayList<>();
//
//        this.circles = new HashSet<>();
//        this.trianglesStack = new Stack<>();
//    }
//
//    public void triangulate(){
//        nonconvexTriangulation();
//        convexTriangulation();
//        trianglesSet = new HashSet<>(triangles);
//        trianglesStack.addAll(triangles);
//    }
//
//    public void secondPass(){
//        while (!trianglesStack.isEmpty()){
//            rebuildTriangles(trianglesStack.pop());
//        }
//        trianglesStack.addAll(trianglesSet);
//    }
//
//    public void paintWhite(){
//        for (Triangle triangle: trianglesStack){
//            triangle.setColor(WHITE);
//        }
//    }
//
//    private void rebuildTriangles(Triangle triangle){
//        Set<Triangle> neighbours = triangle.getNeighbours();
//        for (Triangle neighbour : neighbours) {
//            if (trianglesSet.contains(neighbour)) {
//                rebuildTriangles(triangle, neighbour);
//            }
//        }
//    }
//
//    private void rebuildTriangles(Triangle triangle1, Triangle triangle2) {
//        if (trianglesSet.contains(triangle1)
//                && trianglesSet.contains(triangle2)
//                && (!triangle1.getCircumCircle().doNotContainPoints(points)
//                || !triangle2.getCircumCircle().doNotContainPoints(points))){
//
//            Set<Point> commonEdge = Utils.getCommonEdge(triangle1, triangle2);
//
//            Point p1 = triangle1.getLastPoint(commonEdge);
//            Point p2 = triangle2.getLastPoint(commonEdge);
//            Point commonPoint1 = Iterables.get(commonEdge, 0);
//            Point commonPoint2 = Iterables.get(commonEdge, 1);
//            if (doIntersect(p1, p2, commonPoint1, commonPoint2)) {
//                removeTriangle(triangle1);
//                removeTriangle(triangle2);
//
//                Triangle newTriangle1 = new Triangle(p1, p2, commonPoint1);
//                Triangle newTriangle2 = new Triangle(p1, p2, commonPoint2);
//                addTriangle(newTriangle1);
//                addTriangle(newTriangle2);
//
//                newTriangle1.setColor(RED);
//                newTriangle2.setColor(RED);
//
//                flip(newTriangle1, newTriangle2);
//            }
//        }
//    }
//
//
//    private void flip(Triangle triangle1, Triangle triangle2) {
//        Set<Triangle> neighbours1 = triangle1.getNeighbours();
//        neighbours1.remove(triangle2);
//
//        for (Triangle neighbour : neighbours1) {
//            if (trianglesSet.contains(neighbour)) {
//                rebuildTriangles(triangle1, neighbour);
//            }
//        }
//
//        Set<Triangle> neighbours2 = triangle2.getNeighbours();
//        neighbours2.remove(triangle1);
//
//        for (Triangle neighbour : neighbours2) {
//            if (trianglesSet.contains(neighbour)) {
//                rebuildTriangles(triangle2, neighbour);
//            }
//        }
//    }
//
//    private void addTriangle(Triangle triangle){
//        trianglesSet.add(triangle);
//        trianglesStack.add(triangle);
//
//
//        triangle.getFirstPoint().addTriangle(triangle);
//        triangle.getSecondPoint().addTriangle(triangle);
//        triangle.getThirdPoint().addTriangle(triangle);
//
//        Circle circle = Circle.getCircumcircle(triangle).withColor(GRAY);
//        triangle.setCircumCircle(circle);
//        circles.add(circle);
//    }
//
//    private void removeTriangle(Triangle triangle) {
//        for (Point p : triangle.getPoints()) {
//            p.removeTriangle(triangle);
//        }
//
//        trianglesSet.remove(triangle);
//        trianglesStack.remove(triangle);
//
//        circles.removeIf(circle -> Arrays.equals(circle.getCenter(), triangle.getCircumCircle().getCenter()));
//    }
//
//
//
//
//
//
//    private void convexTriangulation() {
//        boolean flag = true;
//        while (flag) {
//            for (int i = 0; i < boundaryPoint.size() - 2; i++) {
//                if (boundaryPoint.get(i + 1).getSumAngels() > 180) {
//                    Triangle triangle = new Triangle(boundaryPoint.get(i), boundaryPoint.get(i + 1), boundaryPoint.get(i + 2));
//                    createTriangleWithColor(triangle, RED);
//                    boundaryPoint.remove(i + 1);
//                    flag = true;
//                    break;
//                } else {
//                    flag = false;
//                }
//            }
//        }
//
//        if (boundaryPoint.get(boundaryPoint.size() - 1).getSumAngels() > 180) {
//            Triangle triangle = new Triangle(boundaryPoint.get(boundaryPoint.size() - 2),
//                    boundaryPoint.get(boundaryPoint.size() - 1),
//                    boundaryPoint.get(0));
//            createTriangleWithColor(triangle, RED);
//            boundaryPoint.remove(boundaryPoint.size() - 1);
//        }
//    }
//
//    private void addBoundaryPoint(Triangle triangle){
//        for (int i = 1; i < boundaryPoint.size(); i++){
//            if (boundaryPoint.get(i).equals(triangle.getFirstPoint()) || boundaryPoint.get(i).equals(triangle.getSecondPoint())) {
//                boundaryPoint.add(i + 1, triangle.getThirdPoint());
//                break;
//            }
//        }
//    }
//
//    private void nonconvexTriangulation(){
//        curPoints.add(points.get(0));
//        curPoints.add(points.get(1));
//        curPoints.add(points.get(2));
//
//
//        boundaryPoint.add(points.get(0));
//        boundaryPoint.add(points.get(1));
//        if (boundaryPoint.get(1).getX() < points.get(2).getX())
//            boundaryPoint.add(1, points.get(2));
//        else
//            boundaryPoint.add(points.get(2));
//
//
//        Triangle triangle = new Triangle(curPoints.get(0), curPoints.get(1), curPoints.get(2));
//        createTriangle(triangle);
//
//        for (int i = 3; i < points.size(); i++) {
//            if (!checkIntersection(curPoints.get(0), curPoints.get(2), curPoints.get(1), points.get(i))) {
//                if (!checkIntersection(curPoints.get(1), curPoints.get(2), curPoints.get(0), points.get(i))) {
//                    Triangle triangle1 = new Triangle(curPoints.get(0), curPoints.get(2), points.get(i));
//                    Triangle triangle2 = new Triangle(curPoints.get(1), curPoints.get(2), points.get(i));
//
//                    if (checkPointInside(triangle1, curPoints.get(1))){
//                        if (checkPointInside(triangle2, curPoints.get(0))){
//                            checkMinAngel(triangle1, triangle2);
//                        } else {
//                            createTriangle(triangle1);
//                            addBoundaryPoint(triangle1);
//                        }
//                    } else {
//                        createTriangle(triangle2);
//                        addBoundaryPoint(triangle2);
//                    }
//                } else {
//                    Triangle triangle1 = new Triangle(curPoints.get(1), curPoints.get(2), points.get(i));
//                    createTriangle(triangle1);
//                    addBoundaryPoint(triangle1);
//                }
//            } else {
//                Triangle triangle2 = new Triangle(curPoints.get(0), curPoints.get(2), points.get(i));
//                createTriangle(triangle2);
//                addBoundaryPoint(triangle2);
//            }
//        }
//    }
//
//
//    private void checkMinAngel(Triangle triangle1, Triangle triangle2) {
//        if (triangle1.getMinAngel() < triangle2.getMinAngel()){
//            createTriangle(triangle2);
//            addBoundaryPoint(triangle2);
//        } else {
//            createTriangle(triangle1);
//            addBoundaryPoint(triangle1);
//        }
//    }
//
//    private void createTriangle(Triangle triangle){
//        triangles.add(triangle);
//        triangle.addAngels();
//
//        triangle.getFirstPoint().addTriangle(triangle);
//        triangle.getSecondPoint().addTriangle(triangle);
//        triangle.getThirdPoint().addTriangle(triangle);
//
//        Circle circle = Circle.getCircumcircle(triangle).withColor(GRAY);
//        triangle.setCircumCircle(circle);
//        circles.add(circle);
//
//        curPoints.clear();
//        curPoints.add(triangle.getFirstPoint());
//        curPoints.add(triangle.getSecondPoint());
//        curPoints.add(triangle.getThirdPoint());
//    }
//
//    private void createTriangleWithColor(Triangle triangle, double[] color){
//        triangle.setColor(color);
//        triangles.add(triangle);
//        triangle.addAngels();
//
//        triangle.getFirstPoint().addTriangle(triangle);
//        triangle.getSecondPoint().addTriangle(triangle);
//        triangle.getThirdPoint().addTriangle(triangle);
//
//        Circle circle = Circle.getCircumcircle(triangle).withColor(GRAY);
//        triangle.setCircumCircle(circle);
//        circles.add(circle);
//
//        curPoints.clear();
//        curPoints.add(triangle.getFirstPoint());
//        curPoints.add(triangle.getSecondPoint());
//        curPoints.add(triangle.getThirdPoint());
//    }
//
//    private boolean checkPointInside(Triangle triangle, Point p){
//        return  (triangle.isPointInside(p).equals(PointPlace.OUTSIDE));
//    }
//
//    private boolean checkIntersection(Point p1, Point p2, Point p3, Point p4){
//        int	dx1 = p2.getX() - p1.getX();
//        int	dy1 = p2.getY() - p1.getY();
//        int	dx2 = p4.getX() - p3.getX();
//        int	dy2 = p4.getY() - p3.getY();
//        int x = dy1 * dx2 - dy2 * dx1;
////        if(!x || !dx2)
////            return false;
//
//        int y = p3.getX() * p4.getY() - p3.getY() * p4.getX();
//        float x_ = (float) ((p1.getX() * p2.getY() - p1.getY() * p2.getX()) * dx2 - y * dx1) / x;
//        float y_ = (float) (dy2 * x - y) / dx2;
//        return ((p1.getX() <= x_ && p2.getX() >= x_) || (p2.getX() <= x_ && p1.getX() >= x_))
//                && ((p3.getX() <= x_ && p4.getX() >= x_) || (p4.getX() <= x_ && p3.getX() >= x_));
//    }
//
//
//    public List<Triangle> getTriangles() {
//        return triangles;
//    }
//    public List<Point> getPoints() {return points;}
//    public Set<Circle> getCircles() {
//        return circles;
//    }
//    public Stack<Triangle> getTrianglesStack() {
//        return trianglesStack;
//    }
//    public Set<Triangle> getTrianglesSet() {
//        return trianglesSet;
//    }
//}
