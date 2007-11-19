import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for the Main game window
 * @author Owner
 *
 */
public class GameWindow extends JFrame {
	JPanel mainPanel;
	Graphics graphics = null;

	
	/**
	 * Constructor
	 * Creates and sets the background color of the main panel
	 */

	public GameWindow (){
		super("Orbit");
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		setVisible(true);
		setSize(new Dimension(640,480));
		Color earth = new Color(189, 183, 107);
		setBackground(earth);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphics = getGraphics();
	}
	
	/**
	 * Draws the player
	 * @param player The player object to be drawn
	 */
	public void draw(Player player){
		
		double tailExtension;
		tailExtension = Math.random() * 10; 
		graphics.setColor(Color.GREEN);
		graphics.fillOval(player.getMainPosition().x-7, player.getMainPosition().y-7, 14, 14);
		graphics.drawOval(player.getThrustPosition().x-2, player.getThrustPosition().y-2, 4, 4);
		if(Orbit.key1 == true)
		{
			
			graphics.drawLine(player.getThrustPosition().x, player.getThrustPosition().y,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
			graphics.drawLine(player.getThrustPosition().x-2, player.getThrustPosition().y-2,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
			graphics.drawLine(player.getThrustPosition().x+2, player.getThrustPosition().y+2,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
		}
	}
	
	/**
	 * Clears the screen 
	 */
	public void clear() {
		graphics.clearRect(0,0, 640, 480);
		
	}
	
	/**
	 * Legacy code for previous method of drawing the map
	 * @param lines ArrayList of Lines to be drawn
	 */
	public void draw(ArrayList<Line> lines){
		for(Line line:lines){
			graphics.drawLine((int)(line.getStartP().x), (int)(line.getStartP().y), (int)(line.getEndP().x), (int)(line.getEndP().y));
		}
	}
	/**
	 * Draws the Title Screen
	 *
	 */
	public void drawTitle(){
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/Title.gif"));
		} 
		catch (IOException e) {
		}
		graphics.drawImage(img, 0, 0, null);
	}
	/**
	 * Draws the Win Screen
	 *
	 */
	public void drawWin(){
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/Win.gif"));
		} 
		catch (IOException e) {
		}
		graphics.drawImage(img, 0, 0, null);
		
	}
}
