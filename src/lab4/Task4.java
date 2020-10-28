package lab4;

/**
 * Created by ArtemBulkhak on 05/04/2019.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {

    private final int step = 2000;
    private final int max = 16000;
    private final int min = 0;
    private int n;

    private double alpha1;
    private double beta1;
    private double alpha2;
    private double beta2;
    private double alpha3;
    private double beta3;

    private double alphaTask1 = 1.427510489793279;
//    private double alphaTask1 = 2.txt.748690328141592;
    private double betaTask1 = 0.9755565487473398;
//    private double betaTask1 = 1.txt.0;
    // если заменить на 1.txt.0, то xi1 получится намного больше

    /**
     * Разница на 200 тыс
     * 17.36482400433774
     * 14882314
     * 5432044622
     * */
    private double alphaTask2 = 7.994708238750027;
    private double betaTask2 = 0.9288042732488392;
//    private double alphaTask1 = 1.txt.21;
//    private double betaTask1 = 0.98;
//    private double alphaTask2 = 3.882;
//    private double betaTask2 = 1.txt.012;

    private List<Double> Xi3;
    private List<Double> Xi4;
    private List<Double> Xi5;
    private List<Double> Xi6;
    private List<Double> Xi3K;
    private List<Double> Xi4K;
    private List<Double> Xi5K;
    private List<Double> Xi6K;

    private List<Double> Xi3Log;
    private List<Double> Xi4Log;
    private List<Double> Xi5Log;
    private List<Double> Xi6Log;

    private List<Double> Xi3Star1;
    private List<Double> Xi3Star2;
    private List<Double> Xi3Star3;

    private double avgLogXi3;
    private double avgLogXi4;
    private double avgLogXi5;
    private double avgLogXi6;

    private double avgSXi3;
    private double avgSXi4;
    private double avgSXi5;
    private double avgSXi6;

    private double pF;
    private double pD;
    private double pL;

    private double nF;
    private double nD;
    private double nL;

    private double xi2 = 857038.0;
    private double xi1;
    private double xi3;


    public Task4(){
        Xi3 = new ArrayList<>();
        Xi4 = new ArrayList<>();
        Xi5 = new ArrayList<>();
        Xi6 = new ArrayList<>();
        Xi3K = new ArrayList<>();
        Xi4K = new ArrayList<>();
        Xi5K = new ArrayList<>();
        Xi6K = new ArrayList<>();

        calcValue();
        calcLog();
        calcS();
        calcBeta();
        calcAlpha();
        calcRes();
        calcP();
        calcResult();
    }


    private void calcResult(){
        xi1 = Math.pow(xi2, betaTask1) * alphaTask1;
        xi3 = calcInverse(alphaTask2, betaTask2, xi1);

        nF = calcInverse(alpha1, beta1, xi3);
        nD = calcInverse(alpha2, beta2, xi3);
        nL = calcInverse(alpha3, beta3, xi3);

        double S = nF*pF*24 + nD*pD*36 + nL*pL*110;
        double s = S / (nF+nD+nL);                  // средняя стоимость билета
        double dailyCost = s*xi2;
        double yearCost = dailyCost*365;

        System.out.println(s);
        System.out.println((long) dailyCost);
        System.out.println((long) yearCost);
    }

    private double calcInverse(double alpha, double beta, double xi){
        return Math.pow((xi/alpha),(1/beta));
    }

    private void calcP(){
        double sum4 = 0;
        double sum5 = 0;
        double sum6 = 0;

        for (Double d: Xi4){
            sum4 += d;
        }
        for (Double d: Xi5){
            sum5 += d;
        }
        for (Double d: Xi6){
            sum6 += d;
        }

        double sum = sum4 + sum5 + sum6;

        pF = sum4/sum;
        pD = sum5/sum;
        pL = sum6/sum;
    }

    private void calcRes(){
        Xi3Star1 = new ArrayList<>();
        Xi3Star2 = new ArrayList<>();
        Xi3Star3 = new ArrayList<>();

        for (int i = 0; i < Xi4K.size(); i++) {
            Xi3Star1.add(alpha1 * Math.pow(Xi4K.get(i), beta1));
        }

        for (int i = 0; i < Xi5K.size(); i++) {
            Xi3Star2.add(alpha2 * Math.pow(Xi5K.get(i), beta2));
        }

        for (int i = 0; i < Xi6K.size(); i++) {
            Xi3Star3.add(alpha3 * Math.pow(Xi6K.get(i), beta3));
        }

        ArrayList<Double> maxs1 = new ArrayList();
        ArrayList<Double> maxs2 = new ArrayList();
        ArrayList<Double> maxs3 = new ArrayList();
        ArrayList<Double> FXiK = F(Xi3K);
        ArrayList<Double> F1XiStar1 = F(Xi3Star1);
        ArrayList<Double> F1XiStar2 = F(Xi3Star2);
        ArrayList<Double> F1XiStar3 = F(Xi3Star3);
        for (int i = 0; i < n+1; i++) {
            maxs1.add(Math.abs(FXiK.get(i) - F1XiStar1.get(i)));
            maxs2.add(Math.abs(FXiK.get(i) - F1XiStar2.get(i)));
            maxs3.add(Math.abs(FXiK.get(i) - F1XiStar3.get(i)));
        }

        double sup1 = Collections.max(maxs1);
        double sup2 = Collections.max(maxs2);
        double sup3 = Collections.max(maxs3);

//        System.out.println("alpha1 = " + alpha1);
//        System.out.println("beta1 = " + beta1);
//        System.out.println("sup = " +  sup1);
//        if (sup1 < D(Xi3, beta1))  System.out.println("yes");
//        else System.out.println("no");
//        System.out.println();
//
//        System.out.println("alpha2 = " + alpha2);
//        System.out.println("beta2 = " + beta2);
//        System.out.println("sup = " +  sup2);
//        if (sup2 < D(Xi3, beta2))  System.out.println("yes");
//        else System.out.println("no");
//        System.out.println();
//
//        System.out.println("alpha3 = " + alpha3);
//        System.out.println("beta3 = " + beta3);
//        System.out.println("sup = " +  sup3);
//        if (sup3 < D(Xi3, beta3))  System.out.println("yes");
//        else System.out.println("no");
//        System.out.println();
    }

    private double D(List<Double> Xi, double beta) {
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

    private void calcAlpha() {
        alpha1 = Math.exp(avgLogXi3 - beta1 * avgLogXi4);
        alpha2 = Math.exp(avgLogXi3 - beta2 * avgLogXi5);
        alpha3 = Math.exp(avgLogXi3 - beta3 * avgLogXi6);
    }

    private void calcBeta() {
        beta1 = Math.sqrt(avgSXi3 / avgSXi4);
        beta2 = Math.sqrt(avgSXi3 / avgSXi5);
        beta3 = Math.sqrt(avgSXi3 / avgSXi6);
    }

    private void calcS(){
        double sumS = 0;
        for (Double d : Xi3Log) {
            sumS += Math.pow(d-avgLogXi3, 2);
        }
        avgSXi3 = sumS / Xi3Log.size();

        double sumS4 = 0;
        double sumS5 = 0;
        double sumS6 = 0;

        for (int i = 0; i < Xi4Log.size(); i++) {
            sumS4 += Math.pow(Xi4Log.get(i)-avgLogXi4, 2);
            sumS5 += Math.pow(Xi5Log.get(i)-avgLogXi5, 2);
            sumS6 += Math.pow(Xi6Log.get(i)-avgLogXi6, 2);
        }

        avgSXi4 = sumS4 / Xi4Log.size();
        avgSXi5 = sumS5 / Xi5Log.size();
        avgSXi6 = sumS6 / Xi6Log.size();
    }

    private void calcLog(){
        Xi3Log = new ArrayList<>();
        Xi4Log = new ArrayList<>();
        Xi5Log = new ArrayList<>();
        Xi6Log = new ArrayList<>();

        double sumLog = 0;
        for (Double d : Xi3) {
            Xi3Log.add(Math.log(d));
            sumLog += Math.log(d);
        }
        avgLogXi3 = sumLog / Xi3.size();

        double sumLog1 = 0;
        double sumLog2 = 0;
        double sumLog3 = 0;
        for (int i = 0; i < Xi4.size(); i++) {
            double d1 = Xi4.get(i);
            double d2 = Xi5.get(i);
            double d3 = Xi6.get(i);
            Xi4Log.add(Math.log(d1));
            Xi5Log.add(Math.log(d2));
            Xi6Log.add(Math.log(d3));
            sumLog1 += Math.log(d1);
            sumLog2 += Math.log(d2);
            sumLog3 += Math.log(d3);
        }
        avgLogXi4 = sumLog1 / Xi4.size();
        avgLogXi5 = sumLog2 / Xi5.size();
        avgLogXi6 = sumLog3 / Xi6.size();
    }

    private void calcValue() {

        Double[] arrRA0 = new Double[]{2.215,1.197,0.841,2.668,1.423,1.067,2.543,1.360,1.004,2.264,1.221,0.865,2.710,1.444,1.088,2.587,1.382,1.026,1.864,1.021,0.665,2.318,1.248,0.892,2.194,1.186,0.830,2.941,1.560,1.204,3.392,1.785,1.429,3.268,1.723,1.367,1.696,0.716,0.936,2.142,0.939,1.159,2.014,0.875,1.095,1.849,0.793,1.013,2.300,1.018,1.238,2.177,0.956,1.176,3.509,1.622,1.842,3.958,1.847,2.067,3.785,2.023,1.819,1.029,0.644,0.440,1.481,0.871,0.667,1.353,0.807,0.603,1.790,1.025,0.821,2.238,1.249,1.045,2.115,1.188,0.984,1.517,0.888,0.684,1.967,1.113,0.909,1.842,1.051,0.847,2.090,1.175,0.971,2.540,1.400,1.196,2.415,1.337,1.133,1.733,0.996,0.792,2.183,1.221,1.017,2.058,1.159,0.955};
        Double[] arrIIA0 = new Double[]{4.252,5.158,4.907,4.349,5.242,4.995,3.550,4.458,4.209,5.704,6.605,6.357,3.348,4.239,3.984,3.654,4.556,4.309,6.973,7.872,7.626,2.113,3.018,2.762,3.636,4.532,4.286,3.089,3.989,3.739,4.235,5.135,4.885,3.521,4.421,4.171};
        Double[] arrRA1 = new Double[]{2.540,1.359,1.003,3.092,1.635,1.279,2.715,1.447,1.091,1.727,0.952,0.596,2.228,1.203,0.847,1.874,1.026,0.670,2.274,1.226,0.870,2.772,1.475,1.119,2.421,1.299,0.943,2.764,1.471,1.115,3.419,1.798,1.442,2.991,1.585,1.229,2.232,0.984,1.204,2.731,1.234,1.454,2.379,1.057,1.277,3.082,1.409,1.629,3.581,1.658,1.878,3.230,1.483,1.703,2.870,1.303,1.523,3.368,1.552,1.772,2.968,1.614,1.410,2.689,1.475,1.271,3.210,1.735,1.531,2.861,1.561,1.357,0.577,0.418,0.214,1.078,0.669,0.465,0.724,0.492,0.288,2.937,1.598,1.394,3.437,1.848,1.644,3.087,1.673,1.469,3.197,1.729,1.525,3.699,1.980,1.776,3.347,1.804,1.600,1.209,0.735,0.531,1.709,0.985,0.781,1.358,0.809,0.605};
        Double[] arrIIA1 = new Double[]{4.902,6.006,5.252,3.275,4.278,3.570,4.369,5.365,4.663,5.350,6.659,5.804,4.419,5.418,4.713,6.119,7.117,6.416,5.695,6.692,5.991,5.434,6.475,5.778,1.209,2.212,1.504,5.929,6.929,6.229,6.450,7.454,6.750,2.474,3.474,2.772};
        Double[] arrRB0 = new Double[]{3.511,1.635,1.819,3.626,1.693,1.877,2.572,1.166,1.350,2.687,1.224,1.407,4.023,1.891,2.075,4.138,1.949,2.133,5.243,2.501,2.685,5.358,2.559,2.743,1.522,0.641,0.825,1.637,0.698,0.882,2.144,0.952,1.136,2.259,1.010,1.193,3.783,1.771,1.955,3.898,1.829,2.013,2.319,1.039,1.223,2.434,1.097,1.281,2.103,0.932,1.115,2.218,0.989,1.173,4.130,1.945,2.129,4.245,2.003,2.186,3.080,1.420,1.604,3.195,1.478,1.661,3.511,1.635,1.819,3.626,1.693,1.877};
        Double[] arrIIB0 = new Double[]{6.965,7.195,5.088,5.318,7.989,8.219,10.429,10.659,2.987,3.217,4.232,4.462,7.509,7.739,4.581,4.811,4.150,4.380,8.204,8.434,6.104,6.334,6.965,7.195};
        Double[] arrRB1 = new Double[]{2.063,0.912,1.095,1.948,0.854,1.038,4.441,2.100,2.284,4.326,2.043,2.227,3.828,1.794,1.978,3.713,1.736,1.920,1.973,0.867,1.051,1.858,0.809,0.993,4.656,2.208,2.392,4.541,2.151,2.334,4.495,2.128,2.311,4.380,2.070,2.254,1.873,0.816,1.000,1.758,0.759,0.943,2.310,1.035,1.219,2.195,0.977,1.161,2.606,1.183,1.367,2.492,1.126,1.310,2.605,1.183,1.366,2.490,1.125,1.309,2.406,1.083,1.267,2.291,1.025,1.209,3.157,1.458,1.642,3.039,1.399,1.583};
        Double[] arrIIB1 = new Double[]{4.070,3.840,8.825,8.595,7.599,7.369,3.8907,3.6607,9.256,9.026,8.934,8.704,3.689,3.459,4.563,4.333,5.156,4.927,5.154,4.923,4.755,4.525,6.257,6.021};
        Double[] arrRC0 = new Double[]{2.200,0.980,1.164,2.815,1.288,1.471,4.202,1.981,2.165,4.817,2.289,2.472,5.793,2.776,2.960,6.408,3.084,3.268,3.301,1.531,1.714,3.916,1.838,2.022,4.201,1.980,2.164,4.816,2.288,2.472,3.021,1.390,1.574,3.634,1.697,1.881,4.364,2.062,2.246,4.982,2.371,2.555,2.482,1.121,1.305,3.097,1.429,1.612,5.154,2.457,2.641,5.769,2.765,2.948,3.719,1.740,1.923,4.333,2.047,2.230,2.391,1.076,1.259,3.006,1.383,1.567,5.534,2.647,2.831,6.149,2.954,3.138};
        Double[] arrIIC0 = new Double[]{4.344,5.574,8.348,9.578,11.529,12.759,6.546,7.776,8.345,9.575,5.985,7.211,8.672,9.907,4.908,6.138,10.252,11.482,7.382,8.610,4.726,5.956,11.011,12.241};
        Double[] arrRC1 = new Double[]{0.671,0.425,0.069,0.725,0.243,0.426,2.773,1.266,1.450,2.888,1.324,1.508,6.325,3.043,3.226,6.442,3.101,3.285,6.074,2.917,3.101,6.189,2.974,3.158,2.639,1.200,1.383,2.754,1.257,1.441,6.536,3.148,3.332,6.649,3.205,3.388,5.134,2.447,2.631,5.249,2.504,2.688,5.291,2.526,2.709,2.617,1.189,1.372,2.730,1.245,1.429,2.773,1.266,1.450,4.965,2.362,2.546,5.080,2.420,2.604,5.122,2.441,2.625,2.043,0.901,1.085,2.043,0.901,1.085,2.110,1.112,1.030,2.156,1.135,1.053,4.664,2.389,2.307,4.780,2.447,2.365,4.822,2.468,2.386,4.475,2.294,2.212,4.590,2.352,2.270};
        Double[] arrIIC1 = new Double[]{1.164,1.394,5.489,5.719,12.594,12.827,12.091,12.321,5.222,5.452,13.016,13.242,10.211,10.441,10.526,5.178,5.404,5.489,9.873,10.103,10.188,4.029,4.252,4.344,9.360,9.592,9.675,8.981,9.211,9.296};

//        Collections.addAll(Xi3, arrIIA0);
//        Collections.addAll(Xi3, arrIIA1);
//        Collections.addAll(Xi3, arrIIB0);
//        Collections.addAll(Xi3, arrIIB1);
//        Collections.addAll(Xi3K, arrIIC0);
//        Collections.addAll(Xi3K, arrIIC1);

        for (int i = 0; i < arrIIA0.length; i++) {
            Xi3.add(arrIIA0[i]*1000);
        }
        for (int i = 0; i < arrIIA1.length; i++) {
            Xi3.add(arrIIA1[i]*1000);
        }
        for (int i = 0; i < arrIIB0.length; i++) {
            Xi3.add(arrIIB0[i]*1000);
        }
        for (int i = 0; i < arrIIB1.length; i++) {
            Xi3.add(arrIIB1[i]*1000);
        }

        for (int i = 0; i < arrIIC0.length; i++) {
            Xi3K.add(arrIIC0[i]*1000);
        }for (int i = 0; i < arrIIC1.length; i++) {
            Xi3K.add(arrIIC1[i]*1000);
        }

        //рабочая выборка
        for (int i = 0; i < 108; i += 3) {
            Xi4.add(arrRA0[i]*1000);
            Xi5.add(arrRA0[i + 1]*1000);
            Xi6.add(arrRA0[i + 2]*1000);

            Xi4.add(arrRA1[i]*1000);
            Xi5.add(arrRA1[i + 1]*1000);
            Xi6.add(arrRA1[i + 2]*1000);
        }

        for (int i = 0; i < 72; i += 3) {
            Xi4.add(arrRB0[i]*1000);
            Xi5.add(arrRB0[i + 1]*1000);
            Xi6.add(arrRB0[i + 2]*1000);

            Xi4.add(arrRB1[i]*1000);
            Xi5.add(arrRB1[i + 1]*1000);
            Xi6.add(arrRB1[i + 2]*1000);

            Xi4K.add(arrRC0[i]*1000);
            Xi5K.add(arrRC0[i + 1]*1000);
            Xi6K.add(arrRC0[i + 2]*1000);
        }

        for (int i = 0; i < 88; i += 3) {
            Xi4K.add(arrRC1[i]*1000);
            Xi5K.add(arrRC1[i + 1]*1000);
            Xi6K.add(arrRC1[i + 2]*1000);
        }

    }
}
