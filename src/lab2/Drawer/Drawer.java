package lab2.Drawer;

/**
 * Created by ArtemBulkhak on 24/02/2019.
 */
import org.lwjgl.glfw.*;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class Drawer {
    public static final double[] GREEN  = {0.0, 1.0, 0.0};
    public static final double[] RED = {1.0, 0.0, 0.0};
    public static final double[] BLUE  = {0.0, 0.0, 1.0};
    public static final double[] YELLOW = {1.0, 1.0, 0.0};
    public static final double[] WHITE = {1.0, 1.0, 1.0};


    public static final double[] GRAY = {0.8, 0.8, 0.8};

    protected long window;

    protected void background() {
        glClearColor(0, 0, 0, 0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glEnable(GL_DEPTH_TEST);
    }

    public Drawer(int width, int height, String name) {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        this.window = GLFW.glfwCreateWindow(width, height, name, 0, 0);

        if (window == 0) {
            throw new RuntimeException("Failed to create window");
        }

        GLFW.glfwMakeContextCurrent(window);
//        glfwShowWindow(window);

        GL.createCapabilities();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // taxi
//        glOrtho(0, 170, 0, 160, 1, -1);
        glOrtho(0, 70, 0, 120, 1, -1);


        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_BLEND);
    }

    public void draw(){
        while (!GLFW.glfwWindowShouldClose(this.window)) {
            background();

            GLFW.glfwSwapBuffers(this.window);
            GLFW.glfwPollEvents();
        }
    }
}