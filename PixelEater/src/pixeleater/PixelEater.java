/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjglprogram;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.lwjgl.input.Keyboard;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class LWJGLProgram {

    int score = 0;
    int scoreCheck = 0;
    double x = 27.5;
    double y = 47.5;
    int[][] gameboard = new int[61][55];
    int height = 61;
    int width = 55;
    TrueTypeFont font;

    public TrueTypeFont myFont() {
        Font awtFont = new Font("Lucida Sans Regular", Font.PLAIN, 20); //name, style (PLAIN, BOLD, or ITALIC), size
        font = new TrueTypeFont(awtFont, false);
        return font;
    }
    public void updateScore() {
		if (score >scoreCheck) {
			Display.setTitle("Score: " + score);
                        scoreCheck = score;
		}
		
	}

    public void start() throws IOException {
        try {
            Display.setDisplayMode(new DisplayMode(550, 660)); //added ten to x and y to prevent out of bound error
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 55, 66, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        File Board = new File("Pacman Board.png");
        getColorValue(Board);
        
        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(0f, 38f, 255f);

            GL11.glBegin(GL11.GL_QUADS);

            drawboard();
            pacman();
            updatePos();
            GL11.glEnd();

            Display.update();
            
        }

        Display.destroy();
    }

    public void getColorValue(File file) throws IOException {

        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                int clr = image.getRGB(j, i);
                gameboard[height - i - 1][j] = clr;

            }
        }

    }

    public void drawboard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int red = (gameboard[j][i] & 0x00ff0000) >> 16;
                int green = (gameboard[j][i] & 0x0000ff00) >> 8;
                int blue = gameboard[j][i] & 0x000000ff;
                GL11.glColor3f(red, green, blue);
                GL11.glVertex2f(i, height - j);
                GL11.glVertex2f((i + 1), height - j);
                GL11.glVertex2f((i + 1), height - j + 1);
                GL11.glVertex2f(i, height - j + 1);
            }
        }
    }

    public void updatePos() {;
        float border = 1.49999f;
        int yPlaceholder = (int) y;
        int xPlaceholder = (int) x;
        int xLeft = (int) (x - border);
        int xRight = (int) (x + border);
        int yDown = (int) (y + border);
        int yUp = (int) (y - border);
        int colorRight = gameboard[height - yPlaceholder][xRight];
        int colorLeft = gameboard[height - yPlaceholder][xLeft];
        int colorTop = gameboard[height - yUp][xPlaceholder];
        int colorBottom = gameboard[height - yDown][xPlaceholder];
        /*  int topR =gameboard[height - yUp][xRight];
         int topL=gameboard[height - yUp][xLeft];
         int botR=gameboard[height - yDown][xRight];
         int botL=gameboard[height - yDown][xLeft];*/
        System.out.println("coordinates: " + (height - yPlaceholder) + "," + xPlaceholder + "Right: " + colorRight + "Left: " + colorLeft + "Top: " + colorTop + "Bottom: " + colorBottom);

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && colorLeft != -16767233) {
            x -= .01;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && colorRight != -16767233) {
            x += .01;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_UP) && colorTop != -16767233) {
            y -= .01;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && colorBottom != -16767233) {
            y += .01;
        }
        if (colorRight == -10240) {
            gameboard[height - yPlaceholder][xRight] = -1;
            score += 1;
        }
        if (colorLeft == -10240) {
            gameboard[height - yPlaceholder][xLeft] = -1;
            score += 1;
        }
        if (colorTop == -10240) {
            gameboard[height - yUp][xPlaceholder] = -1;
            score += 1;
        }
        if (colorBottom == -10240) {
            gameboard[(height - yDown)][xPlaceholder] = -1;
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
        updateScore();
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

    public static void main(String[] argv) throws IOException {
        LWJGLProgram game = new LWJGLProgram();
        game.start();

    }
}
