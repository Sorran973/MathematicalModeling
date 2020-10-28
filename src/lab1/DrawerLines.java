package lab1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.ArrayList;

public class DrawerLines extends ApplicationFrame {
    public DrawerLines(
            String title,
            String title1,
            String title2,
            ArrayList<Double> xsGalilei,
            ArrayList<Double> ysGalilei,
            ArrayList<Double> xsNewton,
            ArrayList<Double> ysNewton,
            String xLabel,
            String yLabel
    ) {
        super(title);

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series1 = new XYSeries(title1);
        for (int i = 0; i < ysGalilei.size(); i++) {
            series1.add(xsGalilei.get(i), ysGalilei.get(i));
        }

        XYSeries series2 = new XYSeries(title2);
        for (int i = 0; i < ysNewton.size(); i++) {
            series2.add(xsNewton.get(i), ysNewton.get(i));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(640, 480));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesLinesVisible(0, false);
        renderer.setBaseLinesVisible(true);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        chart.getXYPlot().setRenderer(renderer);
        setContentPane(panel);
    }

    public void draw() {
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}
