import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class orbit {
	static Boolean key1 = false;
	static Boolean key2 = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		orbit orb1 = new orbit();
		//Initialization
		GameWindow gameWindow = new GameWindow();
		key1 = false;
		key2 = false;
		
		
		//Input
		KeyListener listener = new KeyListener() {
		      public void keyPressed(KeyEvent e) {
		    	  switch (e.getKeyCode()) {
		    	  	case KeyEvent.VK_SPACE: key1 = true;
		    	  	case KeyEvent.VK_C: key2 = true;
		    	  }
		      }

		      public void keyReleased(KeyEvent e) {
		    	  switch (e.getKeyCode()) {
		    	  	case KeyEvent.VK_SPACE: key1 = false;
		    	  	case KeyEvent.VK_C: key2 = false;
		    	  }
		      }

		      public void keyTyped(KeyEvent e) {
		      
		      }

		     
		    };
		    gameWindow.addKeyListener(listener);
		    
	    //MAIN LOOP//
		while(true){
			
		
			//Update
		
			//Display
			System.out.println("Key 1 is :" + key1 + "Key 2 is :" + key2);
		}	
	}

}
