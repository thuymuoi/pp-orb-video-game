import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Orbit {
	static Player player;
	static Boolean key1 = false;
	static Boolean key2 = false;
	static Timer tickTimer;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialization
		GameWindow gameWindow = new GameWindow();
		
		
		//Input
		KeyListener listener = new KeyListener() {
		      public void keyPressed(KeyEvent e) {
		    	  switch (e.getKeyCode()) {
		    	  	case KeyEvent.VK_SPACE: key1 = true; break;
		    	  	case KeyEvent.VK_C: key2 = true; break;
		    	  }
		      }

		      public void keyReleased(KeyEvent e) {
		    	  switch (e.getKeyCode()) {
		    	  	case KeyEvent.VK_SPACE: key1 = false; break;
		    	  	case KeyEvent.VK_C: key2 = false; break;
		    	  }
		      }

		      public void keyTyped(KeyEvent e) {
		      
		      }

		     
		    };
		    gameWindow.addKeyListener(listener);
		    tickTimer = new javax.swing.Timer(17, new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		              tick();
		          }
		       });
		    tickTimer.start();
	}
	
	public static void tick(){
		//Update
	
		//Display
		System.out.println("Key 1 is :" + key1 + "Key 2 is :" + key2);
		
	}

}
