package DZ;

import lab2.Drawer.Drawer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

/**
 * Created by ArtemBulkhak on 16/04/2019.
 */

public class DrawerPoints extends Drawer {

    private ArrayList<double[]> colors;
    private ArrayList<Cluster> clusters;
    private ArrayList<Point> points;
    private ArrayList<Point> centers;


    boolean drawPoints = true;
    boolean drawPointsAfter = false;

    public DrawerPoints(int width, int height, ArrayList<Point> points, ArrayList<Cluster> clusters, ArrayList<Point> centers) {

        super(width, height, "k-means");

        this.clusters = clusters;
        this.points = points;
        this.centers = centers;

        this.colors = new ArrayList<>();
        this.colors.add(GREEN);
        this.colors.add(RED);
        this.colors.add(BLUE);
        this.colors.add(YELLOW);
        this.colors.add(WHITE);


        glfwSetKeyCallback(window, GLFWKeyCallback.create(((window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
//            }
//            } else if (key == GLFW_KEY_C && action == GLFW_PRESS) {
//                toDrawCircles = !toDrawCircles;
//            } else if (key == GLFW_KEY_V && action == GLFW_PRESS) {
//                toDrawWhiteTriangle = !toDrawWhiteTriangle;
//            } else if (key == GLFW_KEY_P && action == GLFW_PRESS) {
//                toDrawPoints = !toDrawPoints;
//            } else if (key == GLFW_KEY_B && action == GLFW_PRESS) {
//                triangulation.paintWhite();
            } else if (key == GLFW_KEY_SPACE && action == GLFW_PRESS) {
                drawPointsAfter = !drawPointsAfter;
                drawPoints = !drawPoints;
            }
        })));
    }

    @Override
    public void draw() {
        while (!org.lwjgl.glfw.GLFW.glfwWindowShouldClose(this.window)) {
            background();


            if (drawPoints)
                drawPoints();

            if (drawPointsAfter) {
                drawPointsAfter();
                drawCenters();
            }

            GLFW.glfwSwapBuffers(this.window);
            GLFW.glfwPollEvents();
        }
    }

    private void drawPoints() {
        glPointSize(4);
        glColor3dv(WHITE);
        for (Point point: points){
            glBegin(GL_POINTS);
            glVertex2d(point.getX(), point.getY());
            glEnd();
        }
    }

    private void drawPointsAfter() {
        int i = 0;
        glPointSize(4);
        for (Cluster cluster: clusters){
            glColor3dv(colors.get(i));
            for (Point point: cluster.getPoints()){
                glBegin(GL_POINTS);
                glVertex2d(point.getX(), point.getY());
                glEnd();
            }
            i++;
        }
    }

    private void drawCenters() {
        glPointSize(6);
        glColor3dv(WHITE);
        for (Point point: centers){
            glBegin(GL_POINTS);
            glVertex2d(point.getX(), point.getY());
            glEnd();
        }
    }
}