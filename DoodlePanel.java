/**
 * @(#)UserPanel.java
 *
 *
 * @author 
 * @version 1.00 2019/3/5
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class DoodlePanel extends JPanel implements KeyListener, ActionListener {
	
	

	private javax.swing.Timer timer = new javax.swing.Timer(5, this);
	private Image img;
	private Player player;
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private ArrayList<MovingPlatform> movingPlatforms = new ArrayList<MovingPlatform>();
	private ArrayList<Spring> springs = new ArrayList<Spring>();
	private boolean started = false;
	private int time = 0;
	private int score = 0, highscore = 0;
		
    public DoodlePanel() {
    	setBackground(Color.WHITE);
 		
 		addKeyListener(this);
 		setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	
 		//Gets image file for background
 		/*img = null;
 		try{
    		img = ImageIO.read(new File("doodlebackground.png"));
    	}catch(IOException e)
    	{
    		e.printStackTrace();
    	}
   		img = img.getScaledInstance(500, 700, java.awt.Image.SCALE_SMOOTH);
   		*/
   		player = new Player(240, 600, 20, 50, Color.BLACK);
   		
   		platforms.add(new Platform(210, 650, 60, 10, Color.GREEN));
   		
   		for(int i = 0; i < 500; i++){
   			int x = (int)(Math.random() * 460);
   			platforms.add(new Platform(x, 500 - i * 100, 60, 10, Color.GREEN));
   			if(Math.random() < .1){
   				springs.add(new Spring(x, 490 - i * 100, 20, 10, Color.BLACK));
   			}
   		}
   		
   		for(int i = 0; i < 500; i++){
   			movingPlatforms.add(new MovingPlatform((int)(Math.random() * 460), -25 - i * 100, 60, 10, Color.BLUE));
   		}
    }
    
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void actionPerformed(ActionEvent E) {
    	time += 5;
    	
    	if(time % 500 == 0){
    		for(int i = 0; i < movingPlatforms.size(); i++){
    			movingPlatforms.get(i).swapDirection();
    		}
    	}
    	
    	for(int i = 0; i < movingPlatforms.size(); i++){
    			if(movingPlatforms.get(i).getDirection())
    				movingPlatforms.get(i).moveRight();
    			else
    				movingPlatforms.get(i).moveLeft();
    		}
    		
    	for(int i = 0; i < platforms.size(); i++){
    		if(checkCollision()) {
    			player.setColliding(true);
    		}
    		else{
    			player.setColliding(false);
    		}
    		
    	}
    	
    	if(player.getY() < 350){
    		score += 350 - player.getY();
    		moveDown(350 - player.getY());
    		player.setY(350);
    	}
    		
    	if(score > highscore)
    		highscore = score;
    	
    	if(player.getY() > 690){
    		end();
    	}

    	repaint();
	
    }
    
    public void moveDown(int x){
    	for(int i = 0; i < platforms.size(); i++){
    		platforms.get(i).moveDown(x);
    	}
    	for(int i = 0; i < movingPlatforms.size(); i++){
    		movingPlatforms.get(i).moveDown(x);
    	}
    	
    	for(int i = 0; i < springs.size(); i++){
    		springs.get(i).moveDown(x);
    	}
    }
    
    public void moveUp(int x){
    	for(int i = 0; i < platforms.size(); i++){
    		platforms.get(i).moveUp(x);
    	}
    	for(int i = 0; i < movingPlatforms.size(); i++){
    		movingPlatforms.get(i).moveUp(x);
    	}
    }
    
    public boolean checkCollision(){
    	
    	Rectangle rec = player.getBounds();
    	
    	int playerY = player.getY();
    	
    	for(Platform p: platforms){
    			
    			Rectangle rec2 = p.getBounds();
    			if(rec.intersects(rec2) && p.getY() - player.getBottomY() < 6)
    				return true;
    		}
    		
    	for(Platform p: movingPlatforms){
    			
    			Rectangle rec2 = p.getBounds();
    			if(rec.intersects(rec2) && p.getY() - player.getBottomY() < 6)
    				return true;
    		}
    		
    	for(Spring s: springs){
    		
    		Rectangle rec2 = s.getBounds();
    			if(rec.intersects(rec2) && s.getY() - player.getBottomY() < 6){
    				player.setJumpAmt(80);
    				player.superJump();
    				return true;
    			}
    		}
			return false;
    	}
    
    public void keyPressed(KeyEvent e) {
    	
    	switch(e.getKeyCode()) {
    		
    		case KeyEvent.VK_LEFT://left arrow
    			if(started)
    				player.moveLeft();
    			break;
    			
    		case KeyEvent.VK_RIGHT: //right arrow
    			if(started)
    				player.moveRight();					
    			break;
    			
    		case KeyEvent.VK_SPACE://spacebar
    			timer.start();
				started = true;
				player.setJumpAmt(40);
    			player.jump();
    			break;
    			
    		default:
    			break;
    	}
    }
    
    	public void end(){
    		timer.stop();
    		player.setX(240);
    		player.setY(580);
    		platforms.add(new Platform(210, 630, 60, 10, Color.GREEN));
    		score = 0;
    	}
    	
    	public void paintComponent(Graphics g){
  
     		 super.paintComponent(g); 	

			 //draws background
     		 //g.drawImage(img, 0, 0, null);
     		 
     		 player.draw(g);
     		 
     		 for(int i = 0; i < platforms.size(); i++){
     		 	platforms.get(i).draw(g);
     		 }
     		 
     		 for(int i = 0; i < movingPlatforms.size(); i++){
     		 	movingPlatforms.get(i).draw(g);
     		 }
     		 
     		 for(int i = 0; i < springs.size(); i++){
     		 	springs.get(i).draw(g);
     		 }
     		 
     		 g.setColor(Color.BLACK);
     		 g.drawString("Score: " + String.valueOf(score), 5, 630);
     		 g.drawString("Highscore: " + String.valueOf(highscore), 5, 650);
     		 	
    	}			 
}
    	
    	
    
    
    