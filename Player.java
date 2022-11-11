/**
 * @(#)Player.java
 *
 *
 * @author Forrest Boyer
 * @version 1.00 2019/5/28
 */

import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Player implements ActionListener{
	
	private int cornerX, cornerY, bottomY; // Top Left Corner
	private int height, width; //width and height of player
  	private Color color; //color of player
  	private javax.swing.Timer jumpTimer = new javax.swing.Timer(10, this);
  	private int airTime = 0;
  	private boolean goingUp = true;
  	private boolean colliding = false;
  	private int jumpAmt = 40;

    public Player(int x, int y, int w, int h, Color c) {
    	cornerX = x;
    	cornerY = y;
    	height = h;
    	width = w;
    	color = c;
    	bottomY = cornerY + 50;
    }
    
   public Color getColor(){
   		return color;
    }
   
   public int getBottomY(){
   		return bottomY;
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
    
    public void moveLeft() {
   		//Left border
    		cornerX -= 20;
    		if(cornerX < 0)
    			cornerX = 480;
    }
    
    public void moveRight() {
    	//Right border
    		cornerX += 20;	
    		if(cornerX > 500)
    			cornerX = 0;
    } 
    
    public void jump(){
    	goingUp = true;
    	jumpTimer.start();
    }
    
    public void setJumpAmt(int j){
    	jumpAmt = j;
    }
    
    public void superJump(){
    	goingUp = true;
    	jumpTimer.start();
    }
    
    public void actionPerformed(ActionEvent E) {
    	
    	if(airTime < jumpAmt && goingUp){
    		cornerY -= 6;
    		airTime += 1;
    	}
    	else if(airTime == jumpAmt && goingUp){
    		goingUp = false;
    		airTime = 0;
    	}
    	else if(!colliding){
    		cornerY += 6;
    	}
    	else if(colliding){
    		colliding = false;
    		goingUp = true;
    		airTime = 0;
    		jumpAmt = 40;
    	}
    }
    
    public boolean getColliding(){
    	return colliding;
    }
    
    public void setColliding(boolean f){
    	colliding = f;
    }
    
    public boolean getDirection(){
    	if(!goingUp)
    		return true;
    	return false;
    }
    
    public void draw(Graphics g){
    	g.setColor(getColor());
    	g.fillRect(getX(),getY(), getWidth(),getHeight());
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}