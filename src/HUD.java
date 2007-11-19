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
	JPanel health;
	int width = 640;
	int height = 100;
	Graphics healthBar;
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HUD hud = new HUD();
	}
*/
	public HUD(){
		frame = new JFrame();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setBackground(Color.RED);
		add(panel);

		health = new JPanel();
		health.setPreferredSize(new Dimension(width,height));
		health.setBackground(Color.GREEN);
		panel.add(health);
		
		frame.setContentPane(panel);
        
        //Display the window.
        frame.setSize(width, height);
        frame.setLocation(0, 481);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
        /*
        System.out.println(panel.getGraphics());
        healthBar = panel.getGraphics();
		healthBar.setColor(Color.GREEN);
		System.out.println(healthBar);
		healthBar.fillRect(0, 0, width, height);
		*/
		
	}
	
	public void updateHealth(int health){
		this.health.setPreferredSize(new Dimension((int)((health/100)*(double)width), height));
	}
	
}
