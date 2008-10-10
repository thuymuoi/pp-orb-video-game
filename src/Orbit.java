/*
    This file is part of Orb.

    Orb is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Orb is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Orb.  If not, see <http://www.gnu.org/licenses/>. 
    
    This software was developed by members of Project:Possibility, a software 
    collaboration for the disabled.
    
    For more information, visit http://projectpossibility.org
*/


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

/**Main game class
 * 
 * @author James Myoung
 *
 */
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
	static KeyListener listener;

/**
 * 
 * @param args
 */
	public static void main(String[] args) {
		//Initialization
		try {
			maps.add(mapLoader.readFile("src/map.txt"));
			maps.add(mapLoader.readFile("src/map2.txt"));
			maps.add(mapLoader.readFile("src/map3.txt"));
			//maps.add(mapLoader.readFile("src/easy_level_1.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		stars = mapLoader.generateStars("src/map.txt");
		player.changeHealth(100);
		hud = new HUD();
		
		gameWindow.setPlayer(player);
		gameWindow.setStars(stars);
		gameWindow.setPolygon(mapLoader.getPolygon(currentLevel));
		

		//Input
		
		listener = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE: key1 = true; break;
				case KeyEvent.VK_C: key2 = true; break;
				case KeyEvent.VK_Z: currentLevel = 2; break; //HACK for testing. Charlene
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

			/** Handles a key release event.
			 * 
			 */
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE: key1 = false; break;
				case KeyEvent.VK_C: key2 = false; break;
				case KeyEvent.VK_Z: currentLevel = 2; break; //HACK for testing. Charlene
				}
				//System.out.println("Key Up");
				thrustSound.stopAudio();
			}

			/** Handles a key hit event.
			 * 
			 */
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
	/**
	 * Tick is a function that is called 30 time a second. It does game updating and displaying.
	 *
	 */
	public static void tick(){
		//Update
		player.update(key1, key2);
		if(player.getMainPosition().x <0 || player.getMainPosition().y < 0 || player.getMainPosition().x > 640 || player.getMainPosition().y > 480){
			player.die();
			player.changeHealth(100);
			hud.updateHealth(player.getHealth());
		}else{
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
			//for(Object star:stars){
			for(int i = 0; i < stars.size(); i++){
				if(collisionDetection.genericCollision(new Position(((Star)stars.get(i)).getX(),((Star)stars.get(i)).getY()),10)){
					player.changeHealth(100);
					hud.updateHealth(player.getHealth());
					System.out.println("COLLECTED A STAR!!!");
					//Update stars to display in game window
					gameWindow.setStars(stars);
					stars.remove(stars.get(i));
					//Update stars to display in game window
					gameWindow.setStars(stars);
					System.out.println("Star #: "+ stars.size());
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
				hud.restart();
			}
			if(stars.size()  == 0){
				player.changeHealth(100);
				hud.updateHealth(player.getHealth());
				System.out.println("Level Passed!");
				if(currentLevel == 0){
					currentLevel++;
					stars = mapLoader.generateStars("src/map2.txt");
					//Update stars to be displayed in game window
					gameWindow.setStars(stars);
					player.setMainPosition(100, 150);
				}
				else if(currentLevel == 1){
					currentLevel++;
					stars = mapLoader.generateStars("src/map3.txt");
					//Update stars to be displayed in game window
					gameWindow.setStars(stars);
					player.setMainPosition(100, 150);
				} 
				else if(currentLevel == 2){
					winScreen();
				}
				else {
					System.out.println("Level Passed!" + currentLevel);
				}
			}
		}
		//Set info for Display in Game Window
		
		gameWindow.setPlayer(player);
		gameWindow.setStars(stars);
		gameWindow.setPolygon(mapLoader.getPolygon(currentLevel));
		
		//gameWindow.clear();

		/*		gameWindow.getGraphics().fillPolygon(mapLoader.getPolygon(currentLevel));

		gameWindow.draw(player);


		for(Star star:stars){
			star.drawStar();
		}
*/
	}	

	/** Displays the splash screen when game starts.
	 * 
	 */
	public static void welcomeScreen(){
		//display code

		while(true){
			if(key1){
				gameWindow.clear();
				key1 = false;
				//Start main game window (animation thread)
				gameWindow.animThread.start();
				break;
				
			}
			gameWindow.drawTitle();
		}
	}

	/** Displays the win screen when the player wins.
	 * 
	 */
	public static void winScreen(){
		//tickTimer.stop();
		//display code
		gameWindow.drawWin();
		tickTimer.stop();
		gameWindow.animThread = null;
		
		//Dispose of offscreen graphics context
		if(gameWindow.offscreenGraphics != null)
		{
			gameWindow.offscreenGraphics.dispose();
		}
		gameWindow.animThread = null;
		//while(gameWindow.animThread == null)
		//{
			gameWindow.drawWin();
			
			
		//}
		
		
		
		System.out.println("WIN SCREEN: " + key1);
		//System.out.println("WIN SCREEN: " + KeyEvent.VK_SPACE);
	
		/*while(true){
			//System.out.println(key1);
			if(key1){
				System.out.println("GOT KEY");
				gameWindow.clear();
				key1 = false;

				currentLevel = 0;
				player.setMainPosition(100, 150);
				welcomeScreen();

				break;
			}
			
		} //for restarting*/
		
		
		
		/*
		winTimer = new javax.swing.Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.clear();
				currentLevel = 0;
				player.setMainPosition(100, 150);
				welcomeScreen();
				//tickTimer.start();

			}
		});

		winTimer.start();
		 */
	}

}



