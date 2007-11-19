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
			bi = ImageIO.read(new File("src/Star.gif")); 
		} catch (IOException e){
			System.out.println("asdf");
		}
        graphics.drawImage(bi, x-10, y-10, null);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
