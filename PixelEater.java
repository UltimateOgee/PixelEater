package pixeleater;

//239
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import javax.imageio.ImageIO;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Pixeleater {

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
    private Audio song;

    public void start() throws IOException {
        try {
            Display.setDisplayMode(new DisplayMode(550, 620)); //added ten to x and y to prevent out of bound error
            Display.create();
            Display.setTitle("Pixel Eater by Nathan and Shubh");

        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        pacman p = new pacman();
        monster m = new monster(27.5, 30.5);
        monster m1 = new monster(30.5, 27.5);

        // init OpenGL here
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 55, 62, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        File file = new File("Pacman Board.png");
        getColorValue(file);
        song = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("green-greens.wav"));
        song.playAsMusic(1.0f, 1.0f, true);
        
       startButton s = new startButton(10, 10);
       // s.init();
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);         
            
            if (state == 0) {

                if (Mouse.getX() > 100 && Mouse.getX() < 450 && Mouse.getY() > 320 && Mouse.getY() < 520) {
                    GL11.glColor3f(1f, 0f, 0f);
                   // s.startButtonInit();

                    if (Mouse.isButtonDown(0)) {
                        state = 1;                       
                        
                    };
                } else {
                    // set the color of the quad (R,G,B,A)
                    GL11.glColor3f(0f, 1f, 0f);
                    System.out.println(Mouse.getX() + ", " + Mouse.getY());
                   // s.startButtonInit();
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
                m1.monster();

                m.updatePos(file, gameboard, height);
                m1.updatePos(file, gameboard, height);
                GL11.glEnd();
            } else {

            }
            Display.update();
        }

        Display.destroy();
    }

    public void updateScore() {

        if (score > scoreCheck) {
            Display.setTitle("Pixel Eater by Nathan and Shubh" + "  Score: " + score);
            scoreCheck = score;
        }
        if (score == 244) {
            Display.setTitle("Pixel Eater by Nathan and Shubh" + "  Score: " + "you win!");
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
        Pixeleater game = new Pixeleater();

        game.start();

    }
}
