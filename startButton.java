//Shubhankar Singh
package pixeleater;

import org.lwjgl.opengl.GL11;


public class startButton {
    
    int x;
    int x1;
    int y;
    int y1;
    
    public startButton(int x, int y){
        this.x = x;
        this.y = y;
        this.x1 = x + 20;
        this.y1 = y + 20;
    }
    
    public int getx(){
        return x;
    }
    public int getx1(){
        return x1;
    }
    public int gety(){
        return y;
    }
    public int gety1(){
        return y1;
    }
    
    public void startButtonInit(){           
        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(10,10);
        GL11.glVertex2f(10+35,10);
        GL11.glVertex2f(10+35,10+20);
        GL11.glVertex2f(10,10+20);
        GL11.glEnd();
    }
    
}
