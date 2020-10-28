package DZ;

import DZ.dz_for_3.Point3;

import java.util.*;

/**
 * Created by ArtemBulkhak on 16/04/2019.
 */

public class KMeans {

    private final Random random;
    private ArrayList<Cluster> clusters;

    public KMeans(final Random random) {
        this.random = random;
        this.clusters = new ArrayList<>();
    }

    public ArrayList<Cluster> cluster(ArrayList<Point> points, final int k) {

        clusters = chooseInitialCenters(points, k, random);
        assignPointsToClusters(clusters, points);

        ArrayList<Cluster> newClusters = new ArrayList<>();

        for (Cluster cluster : clusters) {
            Point newCenter = cluster.centroidOf();

            newClusters.add(new Cluster(newCenter));
        }
        assignPointsToClusters(newClusters, points);


        final int max = Integer.MAX_VALUE;
        for (int count = 1; count < max; count++) {
            boolean clusteringChanged = false;

            for (int i = 0; i < clusters.size(); i++){
                if (!clusters.get(i).getSetPoint().containsAll(newClusters.get(i).getPoints())
                        && !clusters.get(i).getCenter().equals(newClusters.get(i).getCenter())){
                    clusteringChanged = true;
                }
            }
            if (!clusteringChanged) {
                System.out.println(count);
                return clusters;
            } else {
                clusters.clear();
                for (Cluster cluster: newClusters)
                    clusters.add(cluster);
                newClusters.clear();

                for (Cluster cluster : clusters) {
                    Point newCenter = cluster.centroidOf();
                    newClusters.add(new Cluster(newCenter));
                }
                assignPointsToClusters(newClusters, points);
            }
        }
        return clusters;
    }

    private void assignPointsToClusters(ArrayList<Cluster> clusters, ArrayList<Point> points) {

        for (Point p : points) {
            Cluster cluster = getNearestCluster(clusters, p);
            cluster.addPoint(p);
        }
    }

    private ArrayList<Cluster> chooseInitialCenters(ArrayList<Point> points, final int k, final Random random) {

        ArrayList<Cluster> resultSet = new ArrayList<>();

        // Choose one center uniformly at random from among the data points.
//        Point firstPoint = points.remove(random.nextInt(points.size()));
//        Point secondPoint = points.remove(random.nextInt(points.size()));
//        Point thirdPoint = points.remove(random.nextInt(points.size()));


//        for (int i = 0; i < k; i++){
//            resultSet.add(new Cluster(points.get(random.nextInt(points.size()))));
//        }

        for (int i = 0; i < k; i++){
            resultSet.add(new Cluster(points.get(i)));
        }



//        double[] dx2 = new double[points.size()];
//        while (resultSet.size() < k) {
//            // For each data point x, compute D(x), the distance between x and
//            // the nearest center that has already been chosen.
//            int sum = 0;
//
//            for (int i = 0; i < points.size(); i++) {
//                Point p = points.get(i);
//                Cluster nearest = getNearestCluster(resultSet, p);
//                double d = distance(nearest.getCenter(), p);
//                sum += d * d;
//                dx2[i] = sum;
//            }
//
//            // Add one new data point as a center. Each point x is chosen with
//            // probability proportional to D(x)2.txt
//            double r = random.nextDouble() * sum;
//            for (int i = 0 ; i < dx2.length; i++) {
//                if (dx2[i] >= r) {
//                    Point p = points.remove(i);
//                    resultSet.add(new Cluster(p));
//                    break;
//                }
//            }
//        }

        return resultSet;
    }


    private Cluster getNearestCluster(ArrayList<Cluster> clusters, Point point) {

        double minDistance = Double.MAX_VALUE;
        Cluster minCluster = null;

        for (Cluster cluster : clusters) {
            double distance = distance(cluster.getCenter(),point);

            if (distance < minDistance) {
                minDistance = distance;
                minCluster = cluster;
            }
        }
        return minCluster;
    }

    private double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

}


