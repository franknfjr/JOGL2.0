/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

import com.jogamp.opengl.GL2;

/**
 *
 * @author frank
 */
public class Sphere implements IShape {

    private int speed = 0;

    private float rtri = 0.0f;
    private int texture;

    @Override
    public void draw(GL2 gl) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // Move the triangle
        gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

        gl.glBegin(GL2.GL_TRIANGLES);

        //Front
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Front)
        // gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Left Of Triangle (Front)
        //gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Right Of Triangle (Front)

        //Right
        // gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Right)
        //gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Left Of Triangle (Right)
        //  gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Right Of Triangle (Right)

        //LEft
        // gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Back)
        //  gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Left Of Triangle (Back)
        //  gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Right Of Triangle (Back)

        //back
        // gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Left)
        //  gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Left Of Triangle (Left)
        //  gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Right Of Triangle (Left)
        gl.glEnd(); // Done Drawing 3d triangle (Pyramid)
        gl.glFlush();
        rtri += speed;
    }

    @Override
    public void setSpeed(int speed) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.speed = speed;

    }

    @Override
    public void setTexture(int texture) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.texture = texture;

    }

}
