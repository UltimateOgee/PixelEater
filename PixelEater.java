package pixeleater;

//239

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
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

public class PixelEater {
    int score = 0;
    int scoreCheck = 0;
    int[][] gameboard = new int[61][55];
    int height = 61;
    int width = 55;

    //tried using public enums
    //used int instead
    private int state = 0; //start in menu mode = 0
    //goto game = 1
    //goto other = other nums

    public void start() throws IOException {
        try {
            Display.setDisplayMode(new DisplayMode(550, 620)); //added ten to x and y to prevent out of bound error
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        pacman p = new pacman();
        monster m = new monster();
        monster m1 = new monster();
        monster m2 = new monster();
        monster m3 = new monster();
        monster m4 = new monster();
        monster m5 = new monster();
        monster m6 = new monster();
        monster m7 = new monster();
        monster m8 = new monster();
        monster m9 = new monster();
        monster m0 = new monster();
        

        // init OpenGL here
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 55, 62, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        File file = new File("Pacman Board.png");
        getColorValue(file);
        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(0f, 1f, 0f);
            //put the file into memory and then modify the memory

            startButton s = new startButton(10, 10);
            if (state == 0) {

                if (Mouse.getX() > 100 && Mouse.getX() < 450 && Mouse.getY() > 320 && Mouse.getY() < 520) {
                    GL11.glColor3f(1f, 0f, 0f);
                    s.startButtonInit();
                    if (Mouse.isButtonDown(0)) {
                        state = 1;
                    };
                } else {
                    System.out.println(Mouse.getX() + ", " + Mouse.getY());
                    s.startButtonInit();
                }
            } else if (state == 1) {
                GL11.glBegin(GL11.GL_QUADS);
                drawboard();
                p.pacman();
                score = p.getScore();
                scoreCheck = p.getscoreCheck();
                p.updatePos(gameboard, height);
                updateScore();
                m.monster();
                m.updatePos(file, gameboard, height);
                GL11.glEnd();
            } else {

            }
            Display.update();
        }

        Display.destroy();
    }
    public void updateScore() {
		if (score >scoreCheck) {
			Display.setTitle("Score: " + score);
                        scoreCheck = score;
		}
		
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

    public static void main(String[] argv) throws IOException {
        PixelEater game = new PixelEater();
        game.start();

    }
}
