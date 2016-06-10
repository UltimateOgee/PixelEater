package pixeleater;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
  
public class PixelEater {
    
    startButton s = new startButton(100, 100);
  
    public void start() {
        try {
        Display.setDisplayMode(new DisplayMode(800,600));
        Display.create();
    } catch (LWJGLException e) {
        e.printStackTrace();
        System.exit(0);
    }
  
    // init OpenGL
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 800, 0, 600, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    
    while (!Display.isCloseRequested()) {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        
        s.startButtonInit();
        System.out.println(Mouse.getX() + " " + Mouse.getY());
        if((s.getx() < Mouse.getX() && Mouse.getX() < s.getx1()) && (s.gety() < Mouse.getY() && Mouse.getY() < s.gety1())){
            GL11.glColor3f(0f,0f,1f);
            if(Mouse.isButtonDown(0)){
                break;
            }
        }
        else{
            GL11.glColor3f(0.5f,0.5f,1.0f);
        }
        
        Display.update();
    }
    
    //PUT THE GAME CODE HERE NOW
  
    Display.destroy();
    }
  
    public static void main(String[] argv) {
        PixelEater game = new PixelEater();
        game.start();
    }
}