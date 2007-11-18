import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Orbit {
	static Player player = new Player();
	static Boolean key1 = false;
	static Boolean key2 = false;
	static Timer tickTimer;
	static GameWindow gameWindow = new GameWindow();
	static MapLoader mapLoader = new MapLoader();
	static ArrayList maps = new ArrayList();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialization
		try {
			maps.add(mapLoader.readFile());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Input
		KeyListener listener = new KeyListener() {
		      public void keyPressed(KeyEvent e) {
		    	  switch (e.getKeyCode()) {
		    	  	case KeyEvent.VK_SPACE: key1 = true; break;
		    	  	case KeyEvent.VK_C: key2 = true; break;
		    	  }
		    	  //System.out.println("Key Down");
		      }

		      public void keyReleased(KeyEvent e) {
		    	  switch (e.getKeyCode()) {
		    	  	case KeyEvent.VK_SPACE: key1 = false; break;
		    	  	case KeyEvent.VK_C: key2 = false; break;
		    	  }
		    	  //System.out.println("Key Up");
		      }

		      public void keyTyped(KeyEvent e) {}

		     
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
		player.update(key1, key2);
	
		//Display 
		//System.out.println("Main- x:" + player.getMainPosition().x + " y:" + player.getMainPosition().y);
		//System.out.println("Thrust- x:" + player.getThrustPosition().x + " y:" + player.getThrustPosition().y);
		gameWindow.clear();
		gameWindow.draw(player);
		Star star1 = new Star(gameWindow.getGraphics(),250,250);
		star1.drawStar();
		for(Object lineList: maps){
			gameWindow.draw((ArrayList<Line>)lineList);
		}


	}

}
