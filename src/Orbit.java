import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;


public class Orbit {
	static Player player = new Player();
	static Boolean key1 = false;
	static Boolean key2 = false;
	static Timer tickTimer;
	static Timer winTimer;
	static GameWindow gameWindow = new GameWindow();
	static MapLoader mapLoader = new MapLoader(gameWindow);
	static ArrayList maps = new ArrayList();
	static CollisionDetection2 collisionDetection = new CollisionDetection2(player);
	static ArrayList<Star> stars = new ArrayList<Star>();
	static Sound crashSound = new Sound();
	static Sound thrustSound = new Sound();
	static Sound collectSound = new Sound();
	static Polygon level1 = new Polygon();
	static int currentLevel = 0;
	static HUD hud;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialization
		try {
			maps.add(mapLoader.readFile("src/map.txt"));
			maps.add(mapLoader.readFile("src/map2.txt"));
			maps.add(mapLoader.readFile("src/map3.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		stars = mapLoader.generateStars("src/map.txt");
		player.changeHealth(100);
		hud = new HUD();

		//Input
		KeyListener listener = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE: key1 = true; break;
				case KeyEvent.VK_C: key2 = true; break;
				}
				//System.out.println("Key Down");
				try
				{

					thrustSound.playAudio("src/406ship1player2engine.wav");
				}
				catch(Exception e2)
				{           
					System.out.println(e2);
				}
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE: key1 = false; break;
				case KeyEvent.VK_C: key2 = false; break;
				}
				//System.out.println("Key Up");
				thrustSound.stopAudio();
			}

			public void keyTyped(KeyEvent e) {}


		};
		gameWindow.addKeyListener(listener);
		tickTimer = new javax.swing.Timer(34, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		
		welcomeScreen();
		tickTimer.start();
	}

	public static void tick(){
		//Update
		player.update(key1, key2);

		for(Object line: (ArrayList)maps.get(currentLevel)){
			if(collisionDetection.collideWithLine((Line) line)){
				System.out.println("Hit Wall");
				try{crashSound.playAudio("src/bounce.wav");}
				catch(Exception e){System.out.println(e);}
				player.changeHealth(-15);
				hud.updateHealth(player.getHealth());
				break;
			}
			else{
				crashSound.stopAudio();
			}
		}
		for(Object star:stars){
			if(collisionDetection.genericCollision(new Position(((Star)star).getX(),((Star)star).getY()),10)){
				player.changeHealth(100);
				System.out.println("COLLECTED A STAR!!!");
				stars.remove(star);
				try{collectSound.playAudio("src/bicycle_bell.wav");}
				catch(Exception e){System.out.println(e);}
				break;
			}
			else{
				collectSound.stopAudio();  
			}
		}
		//System.out.println("Health: " + player.getHealth());
		if(player.getHealth() <= 0){
			player.die();
		}
		if(stars.size()  == 0){
			System.out.println("Level Passed!");
			if(currentLevel == 0){
				currentLevel++;
				stars = mapLoader.generateStars("src/map2.txt");
				player.setMainPosition(100, 150);
			}
			else if(currentLevel == 1){
				currentLevel++;
				stars = mapLoader.generateStars("src/map3.txt");
				player.setMainPosition(100, 150);
			} else {
				System.out.println("Level Passed!");
			}
		}
		//Display 
		gameWindow.clear();
		
		gameWindow.getGraphics().fillPolygon(mapLoader.getPolygon(currentLevel));
		
		gameWindow.draw(player);
		

		for(Star star:stars){
			star.drawStar();
		}

	}	
	
	public static void welcomeScreen(){
		//display code
		while(true){
			if(key1){
				gameWindow.clear();
				break;
			}
		}
	}
	
	public static void winScreen(){
		tickTimer.stop();
		//display code
		winTimer = new javax.swing.Timer(34, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.clear();
				currentLevel = 0;
				player.setMainPosition(100, 150);
				welcomeScreen();
				tickTimer.start();
			}
		});
		winTimer.start();
	}

}



