/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;


/**
 *
 * @author frank
 */
public class Sphere2 extends JFrame implements GLEventListener {

    private GLU glu = new GLU();
    private float rtri = 0.0f;
    private static float rspeed = 0.5f;

    private static File im = null;
    private int texture;

    public Sphere2() {
        super("OpenGl 2D - Java 2D com opengl Jogl");
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        float ambient[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float specular[] = {1.0f, 1.0f, 1.0f, 1.0f};

        float position[] = {0.0f, 3.0f, 2.0f, 0.0f};
        float lmodel_ambient[] = {0.4f, 0.4f, 0.4f, 1.0f};
        float local_view[] = {0.0f};

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);

        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glClearDepth(1.0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        drawSphere(gl);

        gl.glFlush();

    }

    private void drawSphere(GL2 gl) {
        GLUT glut = new GLUT();
        float no_mat[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_ambient[] = {0.7f, 0.7f, 0.7f, 0.2f, 1.0f};
        float mat_ambient_color[] = {0.7f, 0.7f, 0.7f, 0.2f, 1.0f};
        float mat_diffuse[] = {0.7f, 0.7f, 0.7f, 0.2f, 1.0f};
        float mat_specular[] = {0.7f, 0.7f, 0.7f, 0.2f, 1.0f};
        float no_shininess[] = {0.0f};
        float low_shininess[] = {5.0f};
        float high_shininess[] = {100.0f};
        float mat_emission[] = {0.3f, 0.2f, 0.2f, 0.0f};

        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, low_shininess, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, no_mat, 0);
        glut.glutSolidSphere(2f, 100, 100);
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        if (width <= (height * 2)) {
            gl.glOrtho(-6.0, 6.0, -3.0 * ((float) height * 2) / (float) width,
                    3.0 * ((float) height * 2) / (float) width, -10.0, 10.0);
        } else {
            gl.glOrtho(-6.0 * ((float) width) / ((float) height * 2),
                    6.0 * ((float) width) / (float) height * 2, -3.0, 3.0, -10.0, 10.0);
        }

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    public static void main(String[] args) {
        Sphere2 janela = new Sphere2();

        GLCanvas canvas = new GLCanvas();

        final FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        animator.start();

        canvas.addGLEventListener(janela);

        JSlider j = new JSlider(
                JSlider.HORIZONTAL, 0, 50, 1);

        j.addChangeListener((e) -> {
            JSlider source = (JSlider) e.getSource();
            rspeed = source.getValue();

        });
        JButton jb = new JButton("Choose Texture");

        jb.addActionListener((e) -> {
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                im = selectedFile;
            }
        });
        //JFileChooser jfc = new JFileChooser();
        // int returnValue = jfc.showOpenDialog(null);
        JPanel jp = new JPanel();
        jp.setSize(100, 200);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.add(jb);
        jp.add(j);

        janela.add(jp, BorderLayout.WEST);
        // janela.add(jb,BorderLayout.WEST);
        janela.add(canvas, BorderLayout.CENTER);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(800, 600);
        janela.setVisible(true);
    }

}
