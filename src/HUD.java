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

	/**
	 * @param args
	 */
	JFrame frame;
	JPanel panel;
	int width = 640;
	int height = 50;
	Color RED;
	Color GREEN;
	Graphics healthBar;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HUD hud = new HUD();
	}

	public HUD(){
		//red = new Color (255, 0, 0);
		//green = new Color (0, 255, 0);
		panel = new JPanel();
		panel.setBackground(RED);
		panel.setSize(width, 50);
		healthBar.setColor(GREEN);
		healthBar.drawRect(0, 0, width, height);
		
		add(panel);
		
		frame.setContentPane(panel);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	public void updateHealth(int health){
		healthBar.dispose();
		healthBar.drawRect(0, 0, (health/100)*width, height);
	}
	
}
