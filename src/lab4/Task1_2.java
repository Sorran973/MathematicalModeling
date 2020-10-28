package lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ArtemBulkhak on 01/04/2019.
 */
public class Task1_2 {


    private final int step = 5000;
    private final int max = 50000;
    private final int min = 0;
    private int n;

    private double alpha;
    private double beta;


    private List<Double> Xi1;     // рабочая выборка
    private List<Double> Xi2;     // контрольная выборка

    private List<Double> Xi1Log;
    private List<Double> Xi2Log;

    private List<Double> Xi1Star; // теоретическая выборка, на основе рабочей Xi1(вообще Xi2)

    private double avgLogXi1;
    private double avgLogXi2;
    private double avgSXi1;
    private double avgSXi2;


    public Task1_2(){
        Xi1 = new ArrayList<>();
        Xi2 = new ArrayList<>();
        Xi1Log = new ArrayList<>();
        Xi2Log = new ArrayList<>();

        calcValue();
        calcLog();
        calcS();
        calcBeta();
        calcAlpha();
        System.out.println("alpha = " + alpha);
        System.out.println("beta = " + beta);
        calcRes();

    }

//    private void calcMaxMin(){
//        double max1 = Collections.max(Xi1);
//        double max2 = Collections.max(Xi2);
//
//        if ( max1 > max2)
//            max = (int) max1;
//        else
//            max = (int) max2;
//
//        double min1 = Collections.min(Xi1);
//        double min2 = Collections.min(Xi2);
//
//        if ( min1 < min2)
//            min = (int) min1;
//        else
//            min = (int) min2;
//    }

    private void calcRes(){
        Xi1Star = new ArrayList<>();
        for (int i = 0; i < Xi2.size(); i++) {
            Xi1Star.add(alpha * Math.pow(Xi2.get(i), beta));
        }

        ArrayList<Double> maxs = new ArrayList();
        ArrayList<Double> FXiK = F(Xi1);
        ArrayList<Double> F1XiStar = F(Xi1Star);
        for (int i = 0; i < n+1; i++) {
            maxs.add(Math.abs(FXiK.get(i) - F1XiStar.get(i)));
        }

        double sup = Collections.max(maxs);

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

//    private void calcAlpha(){
//        alpha = Math.exp(avgLogXi1 - beta * avgLogXi2);
//    }
    private void calcAlpha(){
        alpha = Math.exp(avgLogXi1/avgLogXi2);
    }

    // если beta = 1.txt, то alpha = avgLogXi1/avgLogXi2
    // потому что среднее считается как ln, уничтожается и выводится отношение avgLogXi1/avgLogXi2

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

    private void calcValue() {

        Double[] arrThA0 = new Double[]{16551.0, 16810.0, 14434.0, 20891.0, 13773.0, 14739.0, 24713.0, 10127.0, 14689.0, 13047.0, 16487.0, 14345.0};
        Double[] arrRA0 = new Double[] {14899.0, 14292.0, 13046.0, 18696.0, 12468.0, 13313.0, 22040.0, 9278.0, 13269.0, 11833.0, 14843.0, 12968.0};
        Double[] arrThA1 = new Double[]{30746.0, 22558.0, 28001.0, 32958.0, 28277.0, 36763.0, 34650.0, 33590.0, 12239.0, 35848.0, 38451.0, 18573.0};
        Double[] arrRA1 = new Double[] {27320.0, 20155.0, 24916.0, 29255.0, 25159.0, 32398.0, 30735.0, 29808.0, 11126.0, 31784.0, 34061.0, 16668.0};
        Double[] arrThB0 = new Double[]{32822.0, 25314.0, 36918.0, 46677.0, 16909.0, 21889.0, 34998.0, 23285.0, 21561.0, 37778.0, 29376.0, 32822.0};
        Double[] arrRB0 = new Double[]{ 29553.0, 22567.0, 32720.0, 41259.0, 15212.0, 19569.0, 31040.0, 20791.0, 19282.0, 33472.0, 26120.0, 29553.0};
        Double[] arrThB1 = new Double[]{21002.0, 40022.0, 35118.0, 20283.0, 41746.0, 40458.0, 19478.0, 22974.0, 25348.0, 25336.0, 23743.0, 29751.0};
        Double[] arrRB1 = new Double[] {18793.0, 35436.0, 31145.0, 18164.0, 36944.0, 35817.0, 17460.0, 21353.0, 23430.0, 22586.0, 22025.0, 27282.0};
        Double[] arrThC0 = new Double[]{17084.0, 29096.0, 38639.0, 23690.0, 29087.0, 21993.0, 30082.0, 18776.0, 34808.0, 26192.0, 18230.0, 37085.0};
        Double[] arrRC0 = new Double[] {15365.0, 25876.0, 34226.0, 21145.0, 25868.0, 20494.0, 26738.0, 17263.0, 31290.0, 23751.0, 16784.0, 33283.0};
        Double[] arrThC1 = new Double[] {4544.0, 17519.0, 38841.0, 37324.0, 16717.0, 40099.0, 42244.0, 22099.0, 40895.0, 17519.0, 38841.0, 37324.0};
        Double[] arrRC1 = new Double[] {3118.0, 16162.0, 34819.0, 33492.0, 15461.0, 35920.0, 37797.0, 20170.0, 36617.0, 16162.0, 34819.0, 33492.0};

        // заполнение выборок
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
    }
}
