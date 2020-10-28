//package lab2.Drawer;
//
//import lab2.Structures.Circle;
//import lab2.Structures.Point;
//import lab2.Triangulation;
//import org.lwjgl.glfw.GLFW;
//import org.lwjgl.glfw.GLFWKeyCallback;
//
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL11.*;
//
//
//public class TriangleDrawer extends Drawer {
//
//    private static final int NUMBER_OF_SIDES = 50;
//
//    private List<Point> points;
//    private Triangulation triangulation;
//
//    private boolean toDrawCircles;
//    private boolean toDrawWhiteTriangle;
//    private boolean toDrawPoints;
//
//    public TriangleDrawer(int width, int height, Triangulation triangulation, List<Point> points) {
//
//        super(width, height, "triangles");
//
//        this.triangulation = triangulation;
//        this.points = points;
//
//        toDrawCircles = false;
//        toDrawWhiteTriangle = false;
//        toDrawPoints = true;
//
//        glfwSetKeyCallback(window, GLFWKeyCallback.create(((window, key, scancode, action, mods) -> {
//            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
//                glfwSetWindowShouldClose(window, true);
//            } else if (key == GLFW_KEY_C && action == GLFW_PRESS) {
//                toDrawCircles = !toDrawCircles;
//            } else if (key == GLFW_KEY_V && action == GLFW_PRESS) {
//                toDrawWhiteTriangle = !toDrawWhiteTriangle;
//            } else if (key == GLFW_KEY_P && action == GLFW_PRESS) {
//                toDrawPoints = !toDrawPoints;
//            } else if (key == GLFW_KEY_B && action == GLFW_PRESS) {
//                triangulation.paintWhite();
//            } else if (key == GLFW_KEY_SPACE && action == GLFW_PRESS) {
//                triangulation.secondPass();
//            }
//        })));
//
////        glfwSetMouseButtonCallback(window, GLFWMouseButtonCallback.create(((window, button, action, mods) -> {
////            if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
////                double[] x = new double[1.txt];
////                double[] y = new double[1.txt];
////                glfwGetCursorPos(window, x, y);
////
////                triangulation.getPoints().add(new Point((int)x[0], (int)y[0]));
////
////                triangulation.getPoints().sort(Comparator.comparing(Point::getY));
////                Collections.reverse(triangulation.getPoints());
////                System.out.println("points.add(new Point(" + (int) x[0] + ", " + (720 - (int) y[0]) + "));");
////            }
////        })));
//    }
//
//    @Override
//    public void draw() {
//        triangulation.triangulate();
//
//        while (!GLFW.glfwWindowShouldClose(this.window)) {
//            background();
//
//            drawTriangles();
//
//            if (toDrawPoints) {
//                drawPoints();
//            }
//
//            if (toDrawCircles) {
//                drawCircles();
//            }
//
//            GLFW.glfwSwapBuffers(this.window);
//            GLFW.glfwPollEvents();
//        }
//    }
//
//    private void drawTriangles() {
//        if (toDrawWhiteTriangle) {
//            glColor3dv(WHITE);
//            glLineWidth(2);
//            for (int i = 0; i < triangulation.getTrianglesStack().size(); i++) {
//                glBegin(GL_LINE_LOOP);
//                glVertex2d(triangulation.getTrianglesStack().get(i).getFirstPoint().getX(), triangulation.getTrianglesStack().get(i).getFirstPoint().getY());
//                glVertex2d(triangulation.getTrianglesStack().get(i).getSecondPoint().getX(), triangulation.getTrianglesStack().get(i).getSecondPoint().getY());
//                glVertex2d(triangulation.getTrianglesStack().get(i).getThirdPoint().getX(), triangulation.getTrianglesStack().get(i).getThirdPoint().getY());
//                glEnd();
//            }
//        } else {
//            glLineWidth(2);
//            for (int i = 0; i < triangulation.getTrianglesStack().size(); i++) {
//                glBegin(GL_LINE_LOOP);
//                glColor3dv(triangulation.getTrianglesStack().get(i).getColor());
//                glVertex2d(triangulation.getTrianglesStack().get(i).getFirstPoint().getX(), triangulation.getTrianglesStack().get(i).getFirstPoint().getY());
//                glVertex2d(triangulation.getTrianglesStack().get(i).getSecondPoint().getX(), triangulation.getTrianglesStack().get(i).getSecondPoint().getY());
//                glVertex2d(triangulation.getTrianglesStack().get(i).getThirdPoint().getX(), triangulation.getTrianglesStack().get(i).getThirdPoint().getY());
//                glEnd();
//            }
//        }
//
//    }
//
//
//
//    private void drawCircles() {
//        glLineWidth(1);
//        for (Circle circle : triangulation.getCircles()) {
//            glColor3dv(RED);
//            glBegin(GL_LINE_LOOP);
//            for (int i = 0; i < NUMBER_OF_SIDES; i++) {
//                glVertex2d(
//                        (circle.getCenter()[0]) + circle.getRadius() * Math.cos(i * Math.PI * 2 / NUMBER_OF_SIDES),
//                        (circle.getCenter()[1]) + circle.getRadius() * Math.sin(i * Math.PI * 2 / NUMBER_OF_SIDES)
//                );
//            }
//            glEnd();
//        }
//    }
//
//    private void drawPoints() {
//        glColor3dv(WHITE);
//        glPointSize(2);
//        for (Point point : points) {
//            glBegin(GL_POINTS);
//            glVertex2i(point.getX(), point.getY());
//            glEnd();
//        }
//    }
//}