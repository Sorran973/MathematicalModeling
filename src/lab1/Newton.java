package lab1;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by ArtemBulkhak on 14/02/2019.
 */
public class Newton {

    private static final double angle = 45; // 40
    private static final double alpha = Math.toRadians(angle);
    private static final double g = 9.81;
    private static final int v0 = 50; // 50
    private static final double r = 0.1;
    private static final double beta = 0.5 * 0.15 * pow(r, 2) * Math.PI * 1.29;
    private static final double m = 4.0 / 3.0 * PI * pow(r, 3) * 11340;
    private double t_h = 0.01;

    private ArrayList<Double> xs = new ArrayList<>();
    private ArrayList<Double> ys = new ArrayList<>();

    private double u = v0 * cos(alpha);
    private double w = v0 * sin(alpha);


    private double du_dtFunc(double u, double w){
        return (-beta/m) * u * sqrt(u*u + w*w);
    }

    private double dw_dtFunc(double u, double w){
        return -g -(beta/m) * w * sqrt(u*u + w*w);
    }

    public void rungeKutta(){

        ArrayList<Double> k1 = new ArrayList<>();
        ArrayList<Double> k2 = new ArrayList<>();
        ArrayList<Double> k3 = new ArrayList<>();
        ArrayList<Double> k4 = new ArrayList<>();

        double x = 0;
        double y = 0;

        double xLast = x;
        double yLast = y;

        while (y >= 0) {

            k1.clear();
            k2.clear();
            k3.clear();
            k4.clear();

            k1.add(t_h * du_dtFunc(u, w));
            k1.add(t_h * dw_dtFunc(u, w));
            double dx1 = t_h * u;
            double dy1 = t_h * w;

            k2.add(t_h * du_dtFunc(u + k1.get(0) / 2.0, w + k1.get(1) / 2.0));
            k2.add(t_h * dw_dtFunc(u + k1.get(0) / 2.0, w + k1.get(1) / 2.0));
            double dx2 = t_h * (u + k1.get(0) / 2.0);
            double dy2 = t_h * (w + k1.get(1) / 2.0);

            k3.add(t_h * du_dtFunc(u + k2.get(0) / 2.0, w + k2.get(1) / 2.0));
            k3.add(t_h * dw_dtFunc(u + k2.get(0) / 2.0, w + k2.get(1) / 2.0));
            double dx3 = t_h * (u + k2.get(0) / 2.0);
            double dy3 = t_h * (w + k2.get(1) / 2.0);

            k4.add(t_h * du_dtFunc(u + k3.get(0), w + k3.get(1)));
            k4.add(t_h * dw_dtFunc(u + k3.get(0), w + k3.get(1)));
            double dx4 = t_h * (u + k3.get(0));
            double dy4 = t_h * (w + k3.get(1));

            u += (k1.get(0) + 2.0 * k2.get(0) + 2.0 * k3.get(0) + k4.get(0)) / 6.0;
            w += (k1.get(1) + 2.0 * k2.get(1) + 2.0 * k3.get(1) + k4.get(1)) / 6.0;

            xLast = x;
            yLast = y;

            xs.add(xLast);
            ys.add(yLast);


            x += (dx1 + 2.0 * dx2 + 2.0 * dx3 + dx4) / 6.0;
            y += (dy1 + 2.0 * dy2 + 2.0 * dy3 + dy4) / 6.0;

        }

        if (y < 0) {
            x = interpolate(yLast, y, xLast, x);
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

    private static double interpolate(double y1, double y2, double x1, double x2) {
        return x1 + (x2 - x1) * (0 - y1) / (y2 - y1);
    }
}

