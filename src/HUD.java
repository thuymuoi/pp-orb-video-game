/**
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author James Myoung
 *
 */
public class HUD extends JFrame{

	JFrame frame;
	JPanel panel;
	JPanel health;
	int width = 640;
	int height = 50;
	Graphics healthBar;

	/** Constructor.  Creates a JFrame and adds a red JPanel that covers the frame area all the time
	 *  as the background.  Then adds another green JPanel on top which will eventually change in width
	 *  as the player's health changes.
	 */ 
	public HUD(){
		//Creating a frame and adding a red panel as the background.
		frame = new JFrame();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setBackground(Color.RED);
		add(panel);

		//Creating a green panel that will change in width with time.
		health = new JPanel();
		health.setPreferredSize(new Dimension(width,height));
		health.setBackground(Color.GREEN);
		panel.add(health);
	
        //Display the window.
		frame.setContentPane(panel);
        frame.setSize(width, height);
        frame.setLocation(0, 481);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/** Resizes the green health bar according to the player's health.
	 * 
	 * @param health The player's health.
	 */
	public void updateHealth(int health){
		this.health.setSize(new Dimension( (int)((((double)(health))/100)*((double)width)), height) );
	}
	
	/** Returns the green health bar back to 100%.
	 * 
	 */
	public void restart(){
		health.setSize(new Dimension(width, height));
	}
}
