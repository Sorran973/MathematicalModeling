package DZ;

/**
 * Created by ArtemBulkhak on 16/04/2019.
 */
public class Point {

    private double x;
    private double y;

    private int clusterID;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
        this.clusterID = -1;
    }

    public int getClusterID() {
        return clusterID;
    }

    public void setClusterID(int clusterID) {
        this.clusterID = clusterID;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;
        return this.x == point.x && this.y == point.y;
    }
}
