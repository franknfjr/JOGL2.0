/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;

/**
 *
 * @author frank
 */
public class Cube implements IShape {
    
    private int speed = 0;
    
    private float rtri =0.0f;
     private int texture;
     
    @Override
    public void draw(GL2 gl) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        // ----- Render the Pyramid -----
//      gl.glLoadIdentity();                 // reset the model-view matrix
//      gl.glTranslatef(-1.6f, 0.0f, -6.0f); // translate left and into the screen
//      gl.glRotatef(anglePyramid, -0.2f, 1.0f, 0.0f); // rotate about the y-axis
// 
//      gl.glBegin(GL_TRIANGLES); // of the pyramid   
        // ----- Render the Color Cube -----
        gl.glLoadIdentity();                // reset the current model-view matrix
        gl.glTranslatef(1.6f, 0.0f, -7.0f); // translate right and into the screen
//      gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the x, y and z-axes

        gl.glBegin(GL_QUADS); // of the color cube

        // Top-face
        gl.glColor3f(0.0f, 1.0f, 0.0f); // green
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);

        // Bottom-face
        gl.glColor3f(1.0f, 0.5f, 0.0f); // orange
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);

        // Front-face
        gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);

        // Back-face
        gl.glColor3f(1.0f, 1.0f, 0.0f); // yellow
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);

        // Left-face
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);

        // Right-face
        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);

        gl.glEnd(); // of the color cube
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
