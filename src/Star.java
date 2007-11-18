import java.io.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;

public class Star {
	Graphics graphics;
	private BufferedImage bi;
	private int x;
	private int y;
	
	public Star(Graphics graphics, int x, int y){
		this.graphics = graphics;
		bi = null;
		this.x = x;
		this.y = y;
	}
	
	public void drawStar(){
		try {
			bi = ImageIO.read(new File("Star.jpg")); 
		} catch (IOException e){
			System.out.println("asdf");
		}
		graphics = bi.getGraphics();
        graphics.drawImage(bi, x, y, null);
	}
	
}
