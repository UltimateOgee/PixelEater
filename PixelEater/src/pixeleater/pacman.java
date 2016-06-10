/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixeleater;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Shubhankar Singh
 */
public class pacman {
    private double speed; //store the speed the monster can move at

    Texture texture;

    private double x = 27.5;
    private double y = 46.5;

    public pacman(double speed, Texture t) {
        this.speed = speed;
        this.texture = t;
        }
    
    

    public void pacman() { 
        
        // R,G,B,A Set The Color To Blue One Time Only
        GL11.glColor3f(0f, 0f, 0f);

        // draw quad
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, 0);
        GL11.glTranslatef((float) -x, (float) -y, 0);

        GL11.glBegin(GL11.GL_QUADS);
        glTexCoord2f((float) x - 1, (float) y - 1);
        glTexCoord2f((float) x + 1, (float) y - 1);
        glTexCoord2f((float) x + 1, (float) y + 1);
        glTexCoord2f((float) x - 1, (float) y + 1);
        GL11.glVertex2f((float) x - 1, (float) y - 1);
        GL11.glVertex2f((float) x + 1, (float) y - 1);
        GL11.glVertex2f((float) x + 1, (float) y + 1);
        GL11.glVertex2f((float) x - 1, (float) y + 1);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public double getx(){
        return x;
    }
    
    public double gety(){
        return y;
    }

    public void updatePos(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);

        float border = 1.4f;

        int colortr = image.getRGB((int) (x + border), (int) (y + border));
        int colortl = image.getRGB((int) (x - border), (int) (y + border));
        int colorbr = image.getRGB((int) (x + border), (int) (y - border));
        int colorbl = image.getRGB((int) (x - border), (int) (y - border));

        System.out.println("Color: " + colortr + " Coordinates: " + x + ", " + y);

        double s = speed;

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {x -= s;}
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {x += s;}

        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {y -= s;}
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {y += s;}

        if ((colortr == -16767233)) {x -= s;y -= s;}
        if ((colortl == -16767233)) {x += s;y -= s;}
        if ((colorbr == -16767233)) {x -= s;y += s;}
        if ((colorbl == -16767233)) {x += s;y += s;}

        if (x < 0) {x = 0;}
        if (x > 550) {x = 550;}
        if (y < 0) {y = 0;}
        if (y > 610) {y = 610;}
    }
    
}
