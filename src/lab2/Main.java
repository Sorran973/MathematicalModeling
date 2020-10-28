//package lab2;
//
//import lab2.Drawer.TriangleDrawer;
//import lab2.Structures.Point;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * Created by ArtemBulkhak on 23/02/2019.
// */
//public class Main {
//
//    public static final int WINDOW_SIZE = 720;
//    public static final int NUMBER_OF_POINTS = 10;
//
//
//    private static ArrayList<Point> points;
//
//    public static void main(String[] args) throws IOException {
//
//        points = new ArrayList<>();
//
//        generatePoints();
//        showTriangulation(points);
//    }
//
//    public static void generatePoints() throws IOException {
//
//        /** все работает */
//
////        points.add(new Point(366, 708));
////        points.add(new Point(163, 695));
////        points.add(new Point(653, 684));
////        points.add(new Point(95, 605));
////        points.add(new Point(358, 511));
////        points.add(new Point(412, 333));
////        points.add(new Point(698, 279));
////        points.add(new Point(202, 266));
////        points.add(new Point(124, 112));
////        points.add(new Point(289, 98));
//
////        points.add(new Point(314, 700));
////        points.add(new Point(614, 699));
////        points.add(new Point(159, 628));
////        points.add(new Point(284, 485));
////        points.add(new Point(660, 475));
////        points.add(new Point(535, 458));
////        points.add(new Point(336, 361));
////        points.add(new Point(60, 205));
////        points.add(new Point(388, 85));
////        points.add(new Point(639, 41));
//
//        /** не работает rebuild */
//
////        points.add(new Point(350, 717));
////        points.add(new Point(581, 710));
////        points.add(new Point(312, 688));
////        points.add(new Point(524, 684));
////        points.add(new Point(30, 639));
////        points.add(new Point(81, 638));
////        points.add(new Point(548, 489));
////        points.add(new Point(37, 314));
////        points.add(new Point(444, 233));
////        points.add(new Point(234, 215));
//
//
////        points.add(new Point(342,714));
////        points.add(new Point(451,684));
////        points.add(new Point(179,611));
////        points.add(new Point(139,436));
////        points.add(new Point(471,404));
////        points.add(new Point(685,358));
////        points.add(new Point(313,242));
////        points.add(new Point(166,214));
////        points.add(new Point(718,89));
////        points.add(new Point(538,60));
//        /***********************************/
//
////        points.add(new Point(301,700));
////        points.add(new Point(407,680));
////        points.add(new Point(273,606));
////        points.add(new Point(132,538));
////        points.add(new Point(683,486));
////        points.add(new Point(235,474));
////        points.add(new Point(83,421));
////        points.add(new Point(446,417));
////        points.add(new Point(55,385));
////        points.add(new Point(447,312));
////        points.add(new Point(705,289));
////        points.add(new Point(325,247));
////        points.add(new Point(689,191));
////        points.add(new Point(122,136));
////        points.add(new Point(197,16));
//
//
//        switch(NUMBER_OF_POINTS) {
//            case 10:
//                generate10();
//                break;
//            case 12:
//                generate12_1();
//                break;
//            case 15:
//                generate12();
//                break;
//            default:
//                generateRandom();
//                break;
//        }
//
//
//
//
//
//        points.sort(Comparator.comparing(Point::getY));
//        Collections.reverse(points);
//        for (int i = 0; i < points.size(); i++){
//            points.get(i).setId(i);
//        }
//
//        FileWriter fileWriter = new FileWriter("/Users/admin/IdeaProjects/Math_Modeling/src/lab2/pointsBefore.txt");
//        for (int i = 0; i < points.size(); i++){
//            fileWriter.write(points.get(i).toString() + "\n");
//        }
//        fileWriter.close();
//
////        List<Point> points2 = new ArrayList<>(points);
////        points.clear();
////
////        for (Point point: points2){
////            points.add(point);
////        }
//
//
////
////        String str = "";
////        FileReader fileReader = new FileReader("/Users/admin/IdeaProjects/Math_Modeling/src/lab2/pointsBefore.txt");
////        Scanner scanner = new Scanner(fileReader);
////        for (int i = 0; i < NUMBER_OF_POINTS; i++){
////            str = String.valueOf(scanner.nextLine().split(","));
////        }
////        fileReader.close();
//    }
//
//    public static void generateRandom(){
//        points.add(new Point(random(300, 370), random(700, 720)));
//        points.add(new Point(random(371, 720), random(650, 699)));
//        points.add(new Point(random(0, 299), random(600, 649)));
//
//        for (int i = 0; i < NUMBER_OF_POINTS; i++){
//            points.add(new Point(random(0, 720), random(0, 599)));
//        }
////        int fieldsCount = 1.txt;
////        for (int i = 1.txt; fieldsCount < NUMBER_OF_POINTS; i++) {
////            fieldsCount = (int) Math.pow(i, 2.txt);
////        }
////        boolean[] isFilledField = new boolean[fieldsCount];
////        for (int i = 0; i < fieldsCount; i++) {
////            isFilledField[i] = false;
////        }
////
////        int count = 3;
////        Random random = new Random();
////        while (count < NUMBER_OF_POINTS) {
////            int i = random.nextInt(fieldsCount);
////            if (!isFilledField[i]) {
////                isFilledField[i] = true;
////
////                int size = (int) Math.sqrt(fieldsCount);
////                int xBound = i % size;
////                int yBound = i / size;
////
////                int xLowBound = WINDOW_SIZE / size * xBound;
////                int xHighBound = WINDOW_SIZE / size * (xBound + 1.txt);
////
////                int yLowBound = 0;
////                int yHighBound = 600;
////
////                points.add(new Point(xLowBound + random.nextInt(xHighBound - xLowBound), yLowBound + random.nextInt(yHighBound - yLowBound)));
////                count++;
////            }
////        }
//    }
//
//
//
//    public static void generate10(){
//        points.add(new Point(366, 708));
//        points.add(new Point(163, 695));
//        points.add(new Point(653, 684));
//        points.add(new Point(95, 605));
//        points.add(new Point(358, 511));
//        points.add(new Point(412, 333));
//        points.add(new Point(698, 279));
//        points.add(new Point(202, 266));
//        points.add(new Point(124, 112));
//        points.add(new Point(289, 98));
//    }
//
//    public static void generate12(){
//        points.add(new Point(310,703));
//        points.add(new Point(508,668));
//        points.add(new Point(46,611));
//        points.add(new Point(463,509));
//        points.add(new Point(553,400));
//        points.add(new Point(54,394));
//        points.add(new Point(530,328));
//        points.add(new Point(483,239));
//        points.add(new Point(69,234));
//        points.add(new Point(606,204));
//        points.add(new Point(30,70));
//        points.add(new Point(210,4));
//    }
//
//    public static void generate12_1(){
//        points.add(new Point(324,717));
//        points.add(new Point(436,674));
//        points.add(new Point(90,633));
//        points.add(new Point(326,329));
//        points.add(new Point(60,277));
//        points.add(new Point(360,274));
//        points.add(new Point(646,254));
//        points.add(new Point(30,218));
//        points.add(new Point(583,144));
//        points.add(new Point(427,142));
//        points.add(new Point(91,91));
//        points.add(new Point(401,66));
//    }
//
//    public static int random(){
//        int min = 0;
//        int max = WINDOW_SIZE;
//        max -= min;
//        return (int) (Math.random() * ++max) + min;
//    }
//
//    public static int random(int min, int max){
//        max -= min;
//        return (int) (Math.random() * ++max) + min;
//    }
//
//    public static void showTriangulation(List<Point> points) {
//        Triangulation triangulation = new Triangulation(points);
//
//        TriangleDrawer drawer = new TriangleDrawer(WINDOW_SIZE, WINDOW_SIZE, triangulation, points);
//        drawer.draw();
//
////        System.out.println(triangulation.getCircles());
//    }
//}
