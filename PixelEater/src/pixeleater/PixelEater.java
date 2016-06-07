
package lwjgl.program;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.Sys;

public class LWJGLProgram {
    int score = 0;
    double x = 27.5;
    double y = 46.5;

  public void start() throws IOException {
        try {
            Display.setDisplayMode(new DisplayMode(550, 610)); //added ten to x and y to prevent out of bound error
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 55, 61, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(0f, 38f, 255f);
            //put the file into memory and then modify the memory
            // draw quad
            File file = new File("Pacman Board.png");
            GL11.glBegin(GL11.GL_QUADS);
            getColorValue(file);
            pacman();
            updatePos(file);
            GL11.glEnd();

            Display.update();
        }

        Display.destroy();
    }
    public static void getColorValue(File file) throws IOException {

        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int clr = image.getRGB(i, j);
                int red = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;

                GL11.glColor3f(red, green, blue);
                GL11.glVertex2f(i, j);
                    GL11.glVertex2f(i + 1, j);
                    GL11.glVertex2f(i + 1, j + 1);
                    GL11.glVertex2f(i, j + 1);
                
            }
        }

    }
    public void board(int x, int y){
        int[][]gameboard = new int[61][55];
    }
    public void updateYellow(){
        
    }
    public  void updatePos(File file) throws IOException{
        BufferedImage image = ImageIO.read(file);
        float border = 1.49999f;
        
        int colortr = image.getRGB((int)x + 1, (int)y + 1);
        int colortl = image.getRGB((int)x - 1, (int)y + 1);
        int colorbr = image.getRGB((int)x + 1, (int)y - 1);
        int colorbl = image.getRGB((int)x - 1, (int)y - 1);
        int colorRight = image.getRGB((int) (x + border), (int) (y));
        int colorLeft = image.getRGB((int) (x - border), (int) (y));
        int colorTop = image.getRGB((int) (x), (int) (y + border));
        int colorBottom = image.getRGB((int) (x ), (int) (y - border));
        
        System.out.println("Score: " + score +"Color: " + colorRight+ " Coordinates: " + x + ", " + y);
        
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= .01;
	if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x +=.01;
 
	if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y -= .01;
	if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y += .01;
        
        if((colortr == -16767233)){x-=.01;y-=.01;}
        if((colortl == -16767233)){x+=.01;y-=.01;}
        if((colorbr == -16767233)){x-=.01;y+=.01;}
        if((colorbl == -16767233)){x+=.01;y+=.01;}
        
        if(colorRight == -10240 ||colorLeft == -10240 || colorBottom == -10240 ||colorTop == -10240){
        score += 1;
        }
        
            
        if (x < 0) x = 0;
        if (x > 550) x = 550;
	if (y < 0) y = 0;
	if (y > 610) y = 610;
        if (x < 1.0999999999985015)x = 53.489999999996426;
        if (x > 53.489999999996426) x = 1.1999999999985015;
    }
    public void pacman(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
 
		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3f(0f, 255f, 0f);
 
		// draw quad
		GL11.glPushMatrix();
			GL11.glTranslatef((float)x, (float)y, 0);
			GL11.glTranslatef((float)-x, (float)-y, 0);
 
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f((float)x - 1, (float)y - 1);
				GL11.glVertex2f((float)x + 1, (float)y - 1);
				GL11.glVertex2f((float)x + 1, (float)y + 1);
				GL11.glVertex2f((float)x - 1, (float)y + 1);
			GL11.glEnd();
		GL11.glPopMatrix();
    }

    public static void main(String[] argv) throws IOException {
        LWJGLProgram game = new LWJGLProgram();
        game.start();

    }
}
