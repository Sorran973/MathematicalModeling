package lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ArtemBulkhak on 01/04/2019.
 */
public class Task1 {

    private final double E = 0.1;

    private final int max = 50;
    private final int min = 0;
    private final int step = 5;
    private int n;


    private double alpha;
    private double beta;

    private List<Double> Xi1;     // рабочая выборка
    private List<Double> Xi2;     // рабочая выборка

    private List<Double> Xi1K;    // контрольная выборка
    private List<Double> Xi2K;    // контрольная выборка

    private List<Double> Xi1Log;
    private List<Double> Xi2Log;

    private List<Double> Xi1Star; // теоретическая выборка, на основе рабочей Xi1

    private double avgLogXi1;
    private double avgLogXi2;
    private double avgSXi1;
    private double avgSXi2;


    public Task1(){
        Xi1 = new ArrayList<>();
        Xi2 = new ArrayList<>();
        Xi1K = new ArrayList<>();
        Xi2K = new ArrayList<>();
        Xi1Log = new ArrayList<>();
        Xi2Log = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            calcValue(i);
            calcLog();
            calcS();
            calcBeta();
            calcAlpha();
            System.out.println(i);
            System.out.println("alpha = " + alpha);
            System.out.println("beta = " + beta);
            calcRes();
        }
    }

//    private void calcMaxMin(){
//        double max1 = Collections.max(Xi1K);
//        double max2 = Collections.max(Xi1Star);
//
//        if ( max1 > max2)
//            max = (int) max1;
//        else
//            max = (int) max2;
//
//        double min1 = Collections.min(Xi1K);
//        double min2 = Collections.min(Xi1Star);
//
//        if ( min1 < min2)
//            min = (int) min1;
//        else
//            min = (int) min2;
//    }

    private void calcRes(){
        Xi1Star = new ArrayList<>();
        for (int i = 0; i < Xi2K.size(); i++) {
            Xi1Star.add(alpha * Math.pow(Xi2K.get(i), beta));
        }

        ArrayList<Double> maxs = new ArrayList();
        ArrayList<Double> FXiK = F(Xi1K);
        ArrayList<Double> F1XiStar = F(Xi1Star);
        for (int i = 0; i < step + 1; i++) {
            maxs.add(Math.abs(FXiK.get(i) - F1XiStar.get(i)));
        }

        double sup = Collections.max(maxs);

        System.out.println("sup = " + sup);
        if (sup < E)
            System.out.println("yes");
        else
            System.out.println("no");
    }

//    private double D(List<Double> Xi) {
//        int step = Xi.size();
//        double beta = 0.1.txt;
//
//        return Math.sqrt(-0.5 * Math.log(1.txt - beta) / step);
//    }


    private ArrayList<Double> F(List<Double> Xi) { // функция распределения
        ArrayList<Double> res = new ArrayList<>();

        n = (max - min)/ step;

        int N = Xi.size();

        double count;
        for (int i = 0; i < step+1; i++) {
            count = 0;
            for (int j = 0; j < Xi.size(); j++) {
                double x = Xi.get(j);
                if (x > (min + i * n) && x < (min + i * n + n)) {
                    count++;
                }
            }
            double f;
            if(i == 0)
                f = 0;
            else
                f = res.get(i-1);

            res.add((count / N)+f);
        }
        return res;
    }

    private void calcAlpha(){
        alpha = Math.exp(avgLogXi1 - beta * avgLogXi2);
    }

    private void calcBeta(){
        beta = Math.sqrt(avgSXi1/avgSXi2);
    }

    private void calcS(){
        double sumS = 0;
        for (Double d : Xi1Log) { // отнимать от логов
            sumS += Math.pow(d-avgLogXi1, 2);
        }
        avgSXi1 = sumS / Xi1.size();

        sumS = 0;
        for (Double d : Xi2Log) { // отнимать от логов
            sumS += Math.pow(d-avgLogXi2, 2);
        }
        avgSXi2 = sumS / Xi2.size();
    }

    private void calcLog(){
        Xi1Log.clear();
        Xi2Log.clear();

        double sumLog = 0;
        for (Double d : Xi1) {
            Xi1Log.add(Math.log(d));
            sumLog += Math.log(d);
        }
        avgLogXi1 = sumLog / Xi1.size();

        sumLog = 0;
        for (Double d : Xi2) {
            Xi2Log.add(Math.log(d));
            sumLog += Math.log(d);
        }
        avgLogXi2 = sumLog / Xi2.size();
    }

    private void calcValue(int i) {

        Double[] arrThA0 = new Double[]{16.551, 16.810, 14.434, 20.891, 13.773, 14.739, 24.713, 10.127, 14.689, 13.047, 16.487, 14.345};
        Double[] arrRA0 = new Double[]{14.899, 14.292, 13.046, 18.696, 12.468, 13.313, 22.040, 9.278, 13.269, 11.833, 14.843, 12.968};
        Double[] arrThA1 = new Double[]{30.746, 22.558, 28.001, 32.958, 28.277, 36.763, 34.650, 33.590, 12.239, 35.848, 38.451, 18.573};
        Double[] arrRA1 = new Double[]{27.320, 20.155, 24.916, 29.255, 25.159, 32.398, 30.735, 29.808, 11.126, 31.784, 34.061, 16.668};
        Double[] arrThB0 = new Double[]{32.822, 25.314, 36.918, 46.677, 16.909, 21.889, 34.998, 23.285, 21.561, 37.778, 29.376, 32.822};
        Double[] arrRB0 = new Double[]{29.553, 22.567, 32.720, 41.259, 15.212, 19.569, 31.040, 20.791, 19.282, 33.472, 26.120, 29.553};
        Double[] arrThB1 = new Double[]{21.002, 40.022, 35.118, 20.283, 41.746, 40.458, 19.478, 22.974, 25.348, 25.336, 23.743, 29.751};
        Double[] arrRB1 = new Double[]{18.793, 35.436, 31.145, 18.164, 36.944, 35.817, 17.460, 21.353, 23.430, 22.586, 22.025, 27.282};
        Double[] arrThC0 = new Double[]{17.084, 29.096, 38.639, 23.690, 29.087, 21.993, 30.082, 18.776, 34.808, 26.192, 18.230, 37.085};
        Double[] arrRC0 = new Double[]{15.365, 25.876, 34.226, 21.145, 25.868, 20.494, 26.738, 17.263, 31.290, 23.751, 16.784, 33.283};
        Double[] arrThC1 = new Double[]{4.544, 17.519, 38.841, 37.324, 16.717, 40.099, 42.244, 22.099, 40.895, 17.519, 38.841, 37.324};
        Double[] arrRC1 = new Double[]{3.118, 16.162, 34.819, 33.492, 15.461, 35.920, 37.797, 20.170, 36.617, 16.162, 34.819, 33.492};


        // заполнение рабочих выборок
        Xi1.clear();
        Xi2.clear();
        Collections.addAll(Xi1, arrThA0);
        Collections.addAll(Xi1, arrThB0);
        Collections.addAll(Xi1, arrThC0);
        Collections.addAll(Xi1, arrThA1);
        Collections.addAll(Xi1, arrThB1);
        Collections.addAll(Xi1, arrThC1);

        Collections.addAll(Xi2, arrRA0);
        Collections.addAll(Xi2, arrRB0);
        Collections.addAll(Xi2, arrRC0);
        Collections.addAll(Xi2, arrRA1);
        Collections.addAll(Xi2, arrRB1);
        Collections.addAll(Xi2, arrRC1);

       
        // заполнение контрольных выборок
        Xi1K.clear();
        Xi2K.clear();
        switch (i){
            case 0:
                Collections.addAll(Xi1K, arrThA0);
                Collections.addAll(Xi2K, arrRA0);
            break;
            case 1:
                Collections.addAll(Xi1K, arrThB0);
                Collections.addAll(Xi2K, arrRB0);
                break;
            case 2:
                Collections.addAll(Xi1K, arrThC0);
                Collections.addAll(Xi2K, arrRC0);
                break;
            case 3:
                Collections.addAll(Xi1K, arrThA1);
                Collections.addAll(Xi2K, arrRA1);
                break;
            case 4:
                Collections.addAll(Xi1K, arrThB1);
                Collections.addAll(Xi2K, arrRB1);
                break;
            case 5:
                Collections.addAll(Xi1K, arrThC1);
                Collections.addAll(Xi2K, arrRC1);
                break;
        }
    }
}
