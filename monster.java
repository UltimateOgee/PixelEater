
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixeleater;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
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
public class monster {
    
    int score = 0;
    double x = 39.5;
    double y = 47.5;
    double xp, yp;
    
    public monster(){
    }
    
    public void monster() {

        // R,G,B,A Set The Color To Blue One Time Only
        GL11.glColor3f(1f, 0f, 0f);

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
        
        Random generator = new Random(); 
        int r = generator.nextInt(4);
        
        BufferedImage image = ImageIO.read(file);
        float border = 1.49999f;
        int yPlaceholder = (int) y;
        int xPlaceholder = (int) x;
        int colorRight = gameboard[height - yPlaceholder][xPlaceholder+1];
        int colorLeft = gameboard[height - yPlaceholder][xPlaceholder-1];
        int colorTop = gameboard[(height - yPlaceholder) +1][xPlaceholder];
        int colorBottom = gameboard[(height - yPlaceholder) -1][xPlaceholder];
        System.out.println("coordinates: "+ (height - yPlaceholder) + ","+ xPlaceholder +"Right: "+colorRight + "Left: " +colorLeft + "Top: "+colorTop + "Bottom: " +colorBottom);
        
        if(r == 4 && colorLeft != -16767233){x -= 0.05;};
        if(r == 3 && colorRight != -16767233){x += 0.05;};
        if(r == 2 && colorTop != -16767233){y += 0.05;};
        if(r == 1 && colorBottom != -16767233){y -= 0.05;};
        
    }
}
