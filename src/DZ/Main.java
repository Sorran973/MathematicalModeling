package DZ;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ArtemBulkhak on 16/04/2019.
 */
public class Main {

    public static void main(String[] args) throws Exception {


        /**
         * tip         - чаевые  (разная)
         *
         * fare        - счетчик (разная)
         * improvement - экскурсия (0.0$, 0.3$, 0.5$, 1$,)
         *
         *
         * tolls       - пошлина (разная)
         * */

        /**-----------------------------------------------------------*/


//        FileReader fileReader2 = new FileReader("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/source.txt");
        FileReader fileReader2 = new FileReader("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/2.txt");
        Scanner scan2 = new Scanner(fileReader2);


//        ArrayList<ArrayList<String>> arr2 = new ArrayList<>();
//        ArrayList<String> string = new ArrayList<>();
        ArrayList<String[]> arr2 = new ArrayList<>();
        String[] string = new String[17];

        int k = -1;

        while (scan2.hasNextLine()) {
            k++;
            if (k != 17) {
                string[k] = scan2.nextLine();
            } else {
                arr2.add(string);
                string = new String[17];
                k = -1;
            }
        }


        fileReader2.close();


//        FileWriter fileWriter = new FileWriter("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/data.txt");
        FileWriter fileWriter = new FileWriter("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/ddata.txt");
//        for (int i = 0; i < arr2.size(); i++) {
//            String[] inner = arr2.get(i);
//            inner = Arrays.stream(inner).filter(str -> !str.isEmpty()).collect(Collectors.toList()).toArray(new String[0]);
//            arr2.set(i, inner);
//        }
        for (int j = 0; j < arr2.size(); j++){
//            LocalTime time1 = LocalTime.parse(arr2.get(j)[1], DateTimeFormatter.ofPattern("yyyy MMM d hh':'mm':'ss a", Locale.US));
//            LocalTime time2 = LocalTime.parse(arr2.get(j)[2], DateTimeFormatter.ofPattern("yyyy MMM d hh':'mm':'ss a", Locale.US));
//            long seconds = ChronoUnit.SECONDS.between(time1, time2);
////            long minutes = ChronoUnit.MINUTES.between(time1, time2);
////            long hours = ChronoUnit.HOURS.between(time1, time2);
////            double time = hours + minutes / 60.0 + seconds / 3600.0;
//            double time = seconds / 60.0;


//            String str = arr2.get(j)[10] + " " + arr2.get(j)[13] +  " " + arr2.get(j)[15] + "\n";
//            if (Double.valueOf(time) > 0)
                fileWriter.write(arr2.get(j)[13] + "\n");
//            fileWriter.write(arr2.get(j)[13] + " " + arr2.get(j)[15] + "\n");
        }
        fileWriter.close();
        /**-----------------------------------------------------------*/



////        FileReader fileReader = new FileReader("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/data.txt");
//        FileReader fileReader = new FileReader("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/ddata.txt");
//        Scanner scan = new Scanner(fileReader);
//
//        int i = 0;
//
//        ArrayList<String[]> arr = new ArrayList<>();
//
//        ArrayList<Double> time = new ArrayList<>();
//        ArrayList<Double> distance = new ArrayList<>();
//        ArrayList<Double> pay = new ArrayList<>();
//
//
//        while (scan.hasNextLine()) {
//            arr.add(scan.nextLine().split(" "));
//            i++;
//        }
//
//        int cnt = 0;
//        Iterator iterator = arr.iterator();
//        while (iterator.hasNext()) {
//            String[] str = (String[]) iterator.next();
////            if (Double.valueOf(str[0]) < 9.0 || Double.valueOf(str[0]) > 293) {
////            if (Double.valueOf(str[1]) == 5 || Double.valueOf(str[1]) == 50){
////                double d = Double.valueOf(str[1]) + random(-3.0, 3.0);
////                str[1] = String.valueOf(d);
////            }
//            if (Double.valueOf(str[0]) < 4.9) {
//                iterator.remove();
//                cnt++;
//            }
//
//        }
//        System.out.println(cnt);
//
////        FileWriter fileWriter2 = new FileWriter("/Users/admin/IdeaProjects/Math_Modeling/src/DZ/ddata.txt");
////        for (int j = 0; j < arr.size(); j++){
////            fileWriter2.write(arr.get(j)[0] + " " + arr.get(j)[1] + "\n");
////        }
////        fileWriter2.close();
//
//
//        for (int j = 0; j < arr.size(); j++){
////            if (arr.get(j)[0].equals("//"))
//            time.add(Double.valueOf(arr.get(j)[0]));
//            distance.add(Double.valueOf(arr.get(j)[1]));
////            pay.add(Double.valueOf(arr.get(j)[2]));
//
//        }
//
//
//
//        fileReader.close();
//
//        double time_max = Collections.max(time);
//        double time_min = Collections.min(time);
//
//        double distance_max = Collections.max(distance);
//        double distance_min = Collections.min(distance);
//
////        int pay_max = Collections.max(pay).intValue();
////        int pay_min = Collections.min(pay).intValue();
//
//
//        ArrayList<Point> points = new ArrayList<>();
//
//        /** distance and pay */
//        for (int j = 0; j < arr.size(); j++){
//            points.add(new Point(time.get(j), distance.get(j)));
////            points.add(new Point(distance.get(j), time.get(j)));
//        }
////        for (int j = 0; j < arr.size(); j++){
////            points.add(new Point(normalize(time.get(j), time_min, time_max),
////                    normalize(distance.get(j), distance_min, distance_max)));
////        }
//
//
//        KMeans kMeans = new KMeans(new Random());
//        ArrayList<Cluster> clusters = kMeans.cluster(points, 4);
//
//        clusters.get(0).getPoints().sort(Comparator.comparing(Point::getX));
//        clusters.get(1).getPoints().sort(Comparator.comparing(Point::getX));
//        clusters.get(2).getPoints().sort(Comparator.comparing(Point::getX));
//
//
//
//                ArrayList<Point> centers = new ArrayList<>();
//        for (Cluster cluster: clusters){
//            centers.add(cluster.getCenter());
//        }
//
//        DrawerPoints drawerPoints = new DrawerPoints(720, 720, points, clusters, centers);
//        drawerPoints.draw();
//        System.out.println();

    }

    public static double normalize(double point, int min, int max){
        return point/(max-min);
    }

    public static double random(double min, double max){
        max -= min;
        return (Math.random() * ++max) + min;
    }
}

