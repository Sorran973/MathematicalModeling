package DZ;

import java.util.*;

/**
 * Created by ArtemBulkhak on 16/04/2019.
 */


public class Cluster implements Comparable{

    private Point center;
    private List<Point> points;

    public Set<Point> getSetPoint() {
        return setPoint;
    }

    private Set<Point> setPoint;


    public Cluster(Point center) {
        this.center = center;
        this.points = new ArrayList<Point>();
        this.setPoint = new HashSet<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point centroidOf(){

        double sumX = 0;
        double sumY = 0;
        for (Point point: points){
            sumX += point.getX();
            sumY += point.getY();
        }

        double autoCorrelationX = sumX / points.size();
        double autoCorrelationY = sumY / points.size();
        Point autoCorrelation = new Point(autoCorrelationX, autoCorrelationY);

        double min = Integer.MAX_VALUE;
        int num = -1;

//        for (int i = 0; i < points.size(); i++) {
//            if (distance(autoCorrelation, points.get(i)) < min) {
//                min = distance(autoCorrelation, points.get(i));
//                num = i;
//            }
//        }
        setPoint.clear();
        setPoint.addAll(points);

        return autoCorrelation;
    }

    private double distance(Point p1, Point p2){
        return Math.pow(Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)), 2);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    @Override
    public int compareTo(Object o) {
//        return (int) (this.center.getValueCharactestic() - ((Cluster)o).center.getValueCharactestic());
        return (int) distance(this.center, ((Cluster)o).center);

    }
}
