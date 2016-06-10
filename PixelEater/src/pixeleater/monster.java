//shubhankar 2016
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

public class monster {
    
    private double speed; //store the speed the monster can move at

    Texture texture;

    private double x = 35.5;
    private double y = 20.5;

    public monster(int x, int y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
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

    public void updatePos(File file, pacman p) throws IOException {
        BufferedImage image = ImageIO.read(file);

        float border = 1.4f;

        int colortr = image.getRGB((int) (x + border), (int) (y + border));
        int colortl = image.getRGB((int) (x - border), (int) (y + border));
        int colorbr = image.getRGB((int) (x + border), (int) (y - border));
        int colorbl = image.getRGB((int) (x - border), (int) (y - border));

        System.out.println("ColorJHKJHJK: " + colortr + " Coordinates: " + x + ", " + y);
        double s = 0.04;
        
        /*
        boolean right = true, left = true, top = true, bottom = true;
        if(colortr == -16767233 || colorbr == -16767233){right = true;}
        if(colortl == -16767233 || colorbl == -16767233){left = true;}
        if(colortr == -16767233 || colortl == -16767233){top = true;}
        if(colorbl == -16767233 || colorbr == -16767233){bottom = true;}

        
        double dy = Math.abs(p.gety() - y);
        double dx = Math.abs(p.getx() - x);
        
        //clean this code up later:
        
        if(dy < dx){
            if(right == true && bottom == true){y += s;}
            else if(right == true && top == true){y -= s;}
            else if(left == true && bottom == true){y -= s;}
            else if(left == true && top == true){y += s;}
            else{
                if ((p.getx() + 0.05) > x) {x += s;}
            else if ((p.getx() - 0.05) < x) {x -= s;};
            };
        }
        else{
            if(top == true && right == true){x += s;}
            else if(top ==true && left == true){x -= s;}
            else if(bottom == true && right == true){x -= s;}
            else if(bottom == true && left == true){x += s;}
            else{
                if ((p.gety() + 0.05) > y) {y += s;}
                else if ((p.gety() - 0.05) < y) {y -= s;};
            };
        }
        */
        
        if ((p.gety() + 0.05) > y) {y += s;}
        else if ((p.gety() - 0.05) < y) {y -= s;}
        
        
        if ((p.getx() + 0.05) > x) {x += s;}
        else if ((p.getx() - 0.05) < x) {x -= s;}
        

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
