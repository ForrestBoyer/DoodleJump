/**
 * @(#)Spring.java
 *
 *
 * @author 
 * @version 1.00 2019/5/30
 */

import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Spring extends Platform{

    public Spring(int x, int y, int w, int h, Color c) {
    	super(x,y,w,h,c);
    }
    
    public void draw(Graphics g){
    	g.setColor(getColor());
    	g.fillRect(getX(),getY(), getWidth(),getHeight());
    }
}