import java.io.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;

public class Star {
	Graphics graphics;
	private BufferedImage bi;
	private File f;
	private int x;
	private int y;
	
	public Star(Graphics graphics, int x, int y){
		this.graphics = graphics;
		bi = null;
		f = null;
		this.x = x;
		this.y = y;
	}
	
	public void drawStar(){
		try {
			File f = new File("src/Star.jpg");
			BufferedImage bi = ImageIO.read(f); 
		} catch (IOException e){
			System.out.println("asdf");
		}
        graphics.drawImage(bi, x, y, null);
	}
	
}
