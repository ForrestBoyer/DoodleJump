/**
 * @(#)Platform.java
 *
 * @author Forrest Boyer
 * @version 1.00 2019/5/28
 */
import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Platform {
	
	private int cornerX, cornerY; // Top Left Corner
	private int height, width; //width and height of platform
  	private Color color; //color of platform

    public Platform(int x, int y, int w, int h, Color c) {
    	cornerX = x;
    	cornerY = y;
    	height = h;
    	width = w;
    	color = c;
    }
    
    public Color getColor(){
   		return color;
    }
   
   public void moveDown(int x){
   		cornerY += x;
   }
   
   public void moveUp(int x){
   		cornerY -= x;
   }

   public int getHeight(){
   		return height;
   }
   
    public void setHeight(int h)
   {
   		height = h;
   }
   
    public void setWidth(int w)
   {
   		width = w;
   }
   
    public int getWidth(){
   	  return width;
   }
   
    public int getX(){
   	  return cornerX;
   }
   
    public int getY(){
   	  return cornerY;
   }
   
    public void setX(int x){
   	  cornerX = x;
   }
    public void setY(int x){
   	  cornerY = x;
   }
   
   public void draw(Graphics g){
    	g.setColor(getColor());
    	g.fillRect(getX(),getY(), getWidth(),getHeight());
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
