/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

import com.jogamp.opengl.GL;
import java.awt.Color;
import java.awt.Font;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_COLOR_MATERIAL;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.text.DecimalFormat;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author frank
 */
public class Text3D implements IShape {

    private int speed = 0;
    private float rtri = 0.0f;
    private int texture;
    private float blue = 1;
    private float green = 1;
    private float red = 1;
    private boolean flag = true; // true: color; false: texture
    private GLU glu;  // for the GL Utility
    private static final int CANVAS_WIDTH = 640;  // width of the drawable
    private static final int CANVAS_HEIGHT = 480; // height of the drawable
    private static final int FPS = 60; // animator's target frames per second
    private DecimalFormat formatter = new DecimalFormat("###0.00");

    private TextRenderer textRenderer;
    private String msg = "OpenGl 2D - Java 2D com opengl Jogl";


    public void init(GL2 drawable) {
//        System.out.println("0");

        // Create a animator that drives canvas' display() at the specified FPS. 
        final FPSAnimator animator = new FPSAnimator(FPS, true);

        animator.start(); // start the animation loop

        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
        gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting

        gl.glEnable(GL_LIGHT0); // Enable default light (quick and dirty)
        gl.glEnable(GL_LIGHTING); // Enable lighting
        gl.glEnable(GL_COLOR_MATERIAL); // Enable coloring of material

        // Allocate textRenderer with the chosen font
        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 12));
    }

    public void reshape(GL2 drawable, int x, int y, int width, int height) {
//        System.out.println("1");

        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

        if (height == 0) {
            height = 1;   // prevent divide by zero
        }
        float aspect = (float) width / height;

        // Set the view port (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

        // Enable the model-view transform
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }

    @Override
    public void draw(GL2 drawable) {

        init(drawable);

        reshape(drawable, 0, 0, 640, 480);
//        System.out.println("2");

        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

        // ----- Rendering 3D text using TextRenderer class -----
        textRenderer.begin3DRendering();

        gl.glTranslatef(0.0f, 0.0f, -50.0f);
//        gl.glRotatef(rotateAngle, 1.0f, 0.0f, 0.0f);
//        gl.glRotatef(rotateAngle * 1.5f, 0.0f, 1.0f, 0.0f);
//        gl.glRotatef(rotateAngle * 1.4f, 0.0f, 0.0f, 1.0f);
        gl.glRotatef( rtri, 0.0f, 1.0f, 0.0f );

        // Pulsing Colors Based On Text Position
        textRenderer.setColor((float) (Math.cos(rtri / 20.0f)), // R
                (float) (Math.sin(rtri / 25.0f)), // G
                1.0f - 0.5f * (float) (Math.cos(rtri / 17.0f)), 0.5f); // B

        // String, x, y, z, and scaling - need to scale down
        // Not too sure how to compute the x, y and scaling - trial and error!
        textRenderer.draw3D(msg + formatter.format(rtri / 50), -20.0f,
                0.0f, 0.0f, 0.4f);

        // Clean up rendering
        textRenderer.end3DRendering();

        // Update the rotate angle
        rtri += speed;

    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void setTexture(int texture) {
        this.texture = texture;
    }

    @Override
    public void setColor(Color color) {
        try {
            this.red = (float) color.getRed() / 255;
            this.green = (float) color.getGreen() / 255;
            this.blue = (float) color.getBlue() / 255;
            this.flag = true;

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }

}
