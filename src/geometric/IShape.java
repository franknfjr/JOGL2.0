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
public interface IShape {
    
    public void draw(GL2 gl2);
    public void setSpeed(int speed);

    public void setTexture(int texture);
}