package lab4;

/**
 * Created by ArtemBulkhak on 02/04/2019.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Task2 {

    private final int step = 5000;
    private final int max = 60000;
    private final int min = 0;
    private  int n;

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


    public Task2(){
        Xi1 = new ArrayList<>();
        Xi2 = new ArrayList<>();
        Xi1K = new ArrayList<>();
        Xi2K = new ArrayList<>();

        calcValue();
        calcLog();
        calcS();
        calcBeta();
        calcAlpha();
        System.out.println("alpha = " + alpha);
        System.out.println("beta = " + beta);
        calcRes();
    }

    private void calcRes(){
        Xi1Star = new ArrayList<>();
        for (int i = 0; i < Xi2K.size(); i++) {
            Xi1Star.add(alpha * Math.pow(Xi2K.get(i), beta));
        }

        ArrayList<Double> maxs = new ArrayList();
        ArrayList<Double> FXiK = F(Xi1K);
        ArrayList<Double> F1XiStar = F(Xi1Star);
        for (int i = 0; i < n+1; i++) {
            maxs.add(Math.abs(FXiK.get(i) - F1XiStar.get(i)));
        }

        double sup = Collections.max(maxs);

        System.out.println("D = " + D(Xi1));
        System.out.println("sup = " + sup);
        if (sup < D(Xi1))
            System.out.println("yes");
        else
            System.out.println("no");
    }

    private double D(List<Double> Xi) {
        int n = Xi.size();

        return Math.sqrt(-0.5 * Math.log(1 - beta) / n);
    }


    private ArrayList<Double> F(List<Double> Xi) { // функция распределения
        ArrayList<Double> res = new ArrayList<>();

        n = (max - min)/ step;

        int N = Xi.size();

        double count;
        for (int i = 0; i < n+1; i++) {
            count = 0;
            for (int j = 0; j < Xi.size(); j++) {
                double x = Xi.get(j);
                if (x > (min + i * step) && x < (min + i * step + step)) {
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
        Xi1Log = new ArrayList<>();
        Xi2Log = new ArrayList<>();

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

    private void calcValue() {

        Double[] arrThA0 = new Double[]{16.551, 16.810, 14.434, 20.891, 13.773, 14.739, 24.713, 10.127, 14.689, 13.047, 16.487, 14.345};
        Double[] arrRA0 = new Double[]{4.252,5.158,4.907,4.349,5.242,4.995,3.550,4.458,4.209,5.704,6.605,6.357,3.348,4.239,3.984,3.654,4.556,4.309,6.973,7.872,7.626,2.113,3.018,2.762,3.636,4.532,4.286,3.089,3.989,3.739,4.235,5.135,4.885,3.521,4.421,4.171};
        Double[] arrThA1 = new Double[]{30.746, 22.558, 28.001, 32.958, 28.277, 36.763, 34.650, 33.590, 12.239, 35.848, 38.451, 18.573};
        Double[] arrRA1 = new Double[]{4.902,6.006,5.252,3.275,4.278,3.570,4.369,5.365,4.663,5.350,6.659,5.804,4.419,5.418,4.713,6.119,7.117,6.416,5.695,6.692,5.991,5.434,6.475,5.778,1.209,2.212,1.504,5.929,6.929,6.229,6.450,7.454,6.750,2.474,3.474,2.772};
        Double[] arrThB0 = new Double[]{32.822, 25.314, 36.918, 46.677, 16.909, 21.889, 34.998, 23.285, 21.561, 37.778, 29.376, 32.822};
        Double[] arrRB0 = new Double[]{6.965,7.195,5.088,5.318,7.989,8.219,10.429,10.659,2.987,3.217,4.232,4.462,7.509,7.739,4.581,4.811,4.150,4.380,8.204,8.434,6.104,6.334,6.965,7.195};
        Double[] arrThB1 = new Double[]{21.002, 40.022, 35.118, 20.283, 41.746, 40.458, 19.478, 22.974, 25.348, 25.336, 23.743, 29.751};
        Double[] arrRB1 = new Double[]{4.070,3.840,8.825,8.595,7.599,7.369,3.8907,3.6607,9.256,9.026,8.934,8.704,3.689,3.459,4.563,4.333,5.156,4.927,5.154,4.923,4.755,4.525,6.257,6.021};
        Double[] arrThC0 = new Double[]{17.084, 29.096, 38.639, 23.690, 29.087, 21.993, 30.082, 18.776, 34.808, 26.192, 18.230, 37.085};
        Double[] arrRC0 = new Double[]{4.344,5.574,8.348,9.578,11.529,12.759,6.546,7.776,8.345,9.575,5.985,7.211,8.672,9.907,4.908,6.138,10.252,11.482,7.382,8.610,4.726,5.956,11.011,12.241};
        Double[] arrThC1 = new Double[]{4.544, 17.519, 38.841, 37.324, 16.717, 40.099, 42.244, 22.099, 40.895, 17.519, 38.841, 37.324};
        Double[] arrRC1 = new Double[]{1.164,1.394,5.489,5.719,12.594,12.827,12.091,12.321,5.222,5.452,13.016,13.242,10.211,10.441,10.526,5.178,5.404,5.489,9.873,10.103,10.188,4.029,4.252,4.344,9.360,9.592,9.675,8.981,9.211,9.296};




        for (int i = 0; i < 11; i+=2){
            // заполнение рабочиx выборок
            Xi1.add(arrThA0[i]*1000); // 0,2.txt,4,6,8,10
            Xi1.add(arrThB0[i]*1000);
            Xi1.add(arrThC0[i]*1000);
            Xi1.add(arrThA1[i]*1000);
            Xi1.add(arrThB1[i]*1000);
            Xi1.add(arrThC1[i]*1000);

            // заполнение контрольных выборок
            Xi1K.add(arrThA0[i+1]*1000); // 1.txt,3,5,7,9,11
            Xi1K.add(arrThB0[i+1]*1000);
            Xi1K.add(arrThC0[i+1]*1000);
            Xi1K.add(arrThA1[i+1]*1000);
            Xi1K.add(arrThB1[i+1]*1000);
            Xi1K.add(arrThC1[i+1]*1000);
        }

        for (int i = 0; i < 35; i += 6) {
            // заполнение рабочиx выборок
            Xi2.add(arrRA0[i]*1000);
            Xi2.add(arrRA0[i + 1]*1000);
            Xi2.add(arrRA0[i + 2]*1000);
            Xi2.add(arrRA1[i]*1000);
            Xi2.add(arrRA1[i + 1]*1000);
            Xi2.add(arrRA1[i + 2]*1000);

            // заполнение контрольных выборок
            Xi2K.add(arrRA0[i + 3]*1000);
            Xi2K.add(arrRA0[i + 4]*1000);
            Xi2K.add(arrRA0[i + 5]*1000);
            Xi2K.add(arrRA1[i + 3]*1000);
            Xi2K.add(arrRA1[i + 4]*1000);
            Xi2K.add(arrRA1[i + 5]*1000);
        }

        for (int i = 0; i < 24; i += 4) {
            // заполнение рабочиx выборок
            Xi2.add(arrRB0[i]*1000);
            Xi2.add(arrRB0[i + 1]*1000);
            Xi2.add(arrRB1[i]*1000);
            Xi2.add(arrRB1[i + 1]*1000);
            Xi2.add(arrRC0[i]*1000);
            Xi2.add(arrRC0[i + 1]*1000);

            // заполнение контрольных выборок
            Xi2K.add(arrRB0[i + 2]*1000);
            Xi2K.add(arrRB0[i + 3]*1000);
            Xi2K.add(arrRB1[i + 2]*1000);
            Xi2K.add(arrRB1[i + 3]*1000);
            Xi2K.add(arrRC0[i + 2]*1000);
            Xi2K.add(arrRC0[i + 3]*1000);
        }

        for (int i = 0; i < 11; i += 4) {
            // заполнение рабочиx выборок
            Xi2.add(arrRC1[i]*1000);
            Xi2.add(arrRC1[i + 1]*1000);

            // заполнение контрольных выборок
            Xi2K.add(arrRC1[i + 2]*1000);
            Xi2K.add(arrRC1[i + 3]*1000);
        }

        for (int i = 12; i < 29; i += 6) {
            // заполнение рабочиx выборок
            Xi2.add(arrRC1[i]*1000);
            Xi2.add(arrRC1[i + 1]*1000);
            Xi2.add(arrRC1[i + 2]*1000);

            // заполнение контрольных выборок
            Xi2K.add(arrRC1[i + 3]*1000);
            Xi2K.add(arrRC1[i + 4]*1000);
            Xi2K.add(arrRC1[i + 5]*1000);
        }

    }
}
