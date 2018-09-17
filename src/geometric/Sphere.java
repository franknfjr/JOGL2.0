/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GL2;
import java.awt.Color;

/**
 *
 * @author frank
 */
public class Sphere implements IShape {

    private int speed = 0;
    private float rtri = 0.0f;
    private int texture;
    private float blue = 1;
    private float green = 1;
    private float red = 1;
    private boolean flag = true; // true: color; false: texture

    @Override
    public void draw(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // Move the sphere
        gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);

        if (flag) {
            gl.glColor3f(red, green, blue); // Color
        } else {
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        }
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
        glut.glutSolidSphere(2f, 50, 50);
        gl.glPopMatrix();
        rtri += speed;
    }
    
    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    @Override
    public void setTexture(int texture) {
        this.texture = texture;
        this.flag = false;
    }

    @Override
    public void setColor(Color color) {
        try {
            this.red = (float) color.getRed() / 255;
            this.green = (float) color.getGreen() / 255;
            this.blue = (float) color.getBlue() / 255;
            this.flag = true;

        } catch (Exception e) {
            System.out.println("ERRO:" + e.getMessage());
        }

    }
}
