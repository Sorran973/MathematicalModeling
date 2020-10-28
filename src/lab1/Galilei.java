package lab1;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by ArtemBulkhak on 14/02/2019.
 */
public class Galilei {

    private static final double angle = 45; // 40
    private static final double alpha = Math.toRadians(angle);
    private static final double g = 9.81;
    private static final int v0 = 50; // 50
    private static final double t_h = 0.01;

    private ArrayList<Double> xs = new ArrayList<>();
    private ArrayList<Double> ys = new ArrayList<>();


    private double x(double t) {
        return v0 * Math.cos(alpha) * t;
    }

    private double y(double t) {
        return v0 * Math.sin(alpha) * t - g * Math.pow(t, 2) / 2;
    }

    public void funcGalilei(){

        double t = 0;
        double x = 0;
        double y = 0;
        double xLast = 0;
        double yLast = 0;

        while (y >= 0){
            t += t_h;

            xLast = x;
            yLast = y;

            xs.add(xLast);
            ys.add(yLast);

            y = y(t);
            x = x(t);
        }

        if (y < 0) {
            x = interpolateY(yLast, y, xLast, x);
        }

        xs.add(x);
        ys.add(0.0);

//        for (int i = 0; i < xs.size(); i++) {
//            System.out.println(xs.get(i) + ", " + ys.get(i));
//        }
        System.out.println(xs.get(xs.size()-1));


    }

    public ArrayList<Double> getXs(){
        return this.xs;
    }

    public ArrayList<Double> getYs(){
        return this.ys;
    }

    private double interpolateY(double y1, double y2, double x1, double x2) {
        double k = (0 - y1) / (y2 - y1);
        return x1 + (x2 - x1) * k;
    }
}
