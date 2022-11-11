/**
 * @(#)MovingPlatform.java
 *
 *
 * @author Forrest Boyer
 * @version 1.00 2019/5/30
 */

import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class MovingPlatform extends Platform{
	
  	private boolean movingRight = true;

    public MovingPlatform(int x, int y, int w, int h, Color c) {
    	super(x,y,w,h,c);
    	if(Math.random() > 0.5)
    		movingRight = false;
    }
    
    public void moveLeft() {
   		//Left border
    		if(!(getX() < 0))
    			setX(getX() - 1);
    }
    
    public void moveRight() {
    	//Right border	
    		if(!(getX() > 500))
    			setX(getX() + 1);
    } 
    	
    public void moveDown(int x){
   		setY(getY() + x);
   }
   
   public void moveUp(int x){
   		setY(getY() - x);
   }
    	
   	public void swapDirection(){
   		if(movingRight)
   			movingRight = false;
   		else
   			movingRight = true;
   	}
   	
   	public boolean getDirection(){
   		return movingRight;
   	}
   	public void draw(Graphics g){
    	g.setColor(getColor());
    	g.fillRect(getX(),getY(), getWidth(),getHeight());
    }
}

























