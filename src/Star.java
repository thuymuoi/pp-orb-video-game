import java.io.*;
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;

/**The Star class creates stars that can be collected.  It takes in a prerendered image of a star as a
 * buffered image, which is then converted into a graphics object.  It requires two int inputs, x and y.
 * 
 * @author James Myoung
 *
 */
public class Star {
	Graphics graphics;
	private BufferedImage bi;
	private int x;
	private int y;
	
	/**Constructor.  Creates a graphics instance of a star with the specified x and y coordinates.
	 * 
	 * @param graphics
	 * @param x
	 * @param y
	 */
	public Star(Graphics graphics, int x, int y){
		this.graphics = graphics;
		bi = null;
		this.x = x;
		this.y = y;
	}
	
	/**Draws a star at the specified coordinates
	 * 
	 */
	public void drawStar(){
		try {
			bi = ImageIO.read(new File("src/Star.gif")); 
		} catch (IOException e){
			System.out.println("asdf");
		}
        graphics.drawImage(bi, x-10, y-10, null);
	}

	/**Gets the x coordinate
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**Sets the x coordinate
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**Gets the y coordinate
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**Sets the y coordinate
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
