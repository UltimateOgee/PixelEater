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
    
    int score = 0;
    double x = 27.5;
    double y = 47.5;
    
    public pacman(){
        
    }
    
    public void pacman() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // R,G,B,A Set The Color To Blue One Time Only
        GL11.glColor3f(0f, 255f, 0f);

        // draw quad
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, 0);
        GL11.glTranslatef((float) -x, (float) -y, 0);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f((float) x - 1, (float) y - 1);
        GL11.glVertex2f((float) x + 1, (float) y - 1);
        GL11.glVertex2f((float) x + 1, (float) y + 1);
        GL11.glVertex2f((float) x - 1, (float) y + 1);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public void updatePos(File file, int[][] gameboard, int height) throws IOException {
        BufferedImage image = ImageIO.read(file);
        float border = 1.49999f;
        int yPlaceholder = (int) y;
        int xPlaceholder = (int) x;
        int colorRight = gameboard[height - yPlaceholder][xPlaceholder+1];
        int colorLeft = gameboard[height - yPlaceholder][xPlaceholder-1];
        int colorTop = gameboard[(height - yPlaceholder) +1][xPlaceholder];
        int colorBottom = gameboard[(height - yPlaceholder) -1][xPlaceholder];
        System.out.println("coordinates: "+ (height - yPlaceholder) + ","+ xPlaceholder +"Right: "+colorRight + "Left: " +colorLeft + "Top: "+colorTop + "Bottom: " +colorBottom);

        

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && colorLeft != -16767233) {
            x -= .01;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && colorRight != -16767233) {
            x += .01;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_UP)&&colorTop != -16767233) {
            y -= .01;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)&& colorBottom != -16767233) {
            y += .01;
        }

        if (colorRight == -10240 || colorLeft == -10240 || colorBottom == -10240 || colorTop == -10240) {
            score += 1;
        }

        if (x < 0) {
            x = 0;
        }
        if (x > 550) {
            x = 550;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 610) {
            y = 610;
        }
        if (x < 1.0999999999985015) {
            x = 53.489999999996426;
        }
        if (x > 53.489999999996426) {
            x = 1.1999999999985015;
        }
    }
    
}
