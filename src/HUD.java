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
	int height = 100;
	Color RED;
	Color GREEN;
	Graphics healthBar;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HUD hud = new HUD();
	}

	public HUD(){
		RED = new Color (255, 0, 0);
		GREEN = new Color (0, 255, 0);
		frame = new JFrame();
		frame.setBackground(RED);
		panel = new JPanel();
		panel.setSize(width, 50);
		panel.setLayout(new FlowLayout());
		
		add(panel);
		
		frame.setContentPane(panel);
        
        //Display the window.
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
        System.out.println(panel.getGraphics());
        healthBar = panel.getGraphics();
		healthBar.setColor(GREEN);
		System.out.println(healthBar);
		healthBar.fillRect(2, 2, width-2, height-2);
		
	}
	
	public void updateHealth(int health){
		//System.out.println("oh shoot");
		healthBar.dispose();
		healthBar.fillRect(0, 0, (health/100)*width, height);
	}
	
}
