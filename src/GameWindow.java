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

import java.awt.event.*;
import java.awt.*;

/**
 * Class for the Main game window
 * @author Owner
 *
 */
public class GameWindow extends JFrame implements Runnable{
	Thread animThread;
	Image offscreenImage;
	Graphics offscreenGraphics;
	JPanel mainPanel;
	//JPanel oPanel;
	Graphics graphics = null;
	Player myPlayer = new Player();
	ArrayList<Star> myStars = new ArrayList<Star>();
	Polygon myPolygon = new Polygon();
	

	
	/**
	 * Constructor
	 * Creates and sets the background color of the main panel
	 */

	public GameWindow (){
		super("Orbit");
		
		/**
		 * Window listener
		 */
		/*addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
			
				System.out.println("WINDOW CLOSING!!!!!!");
				//Stop animation thread
				if(animThread != null)
				{
					animThread = null;
				}
				
				//Dispose of offscreen graphics context
				if(offscreenGraphics != null)
				{
					offscreenGraphics.dispose();
				}
				
				//Dispose frame and exit
				dispose();
				System.exit(0);
			}});*/
		
		
			
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		setSize(new Dimension(640,480));
		Color earth = new Color(189, 183, 107);
		setBackground(earth);
		setVisible(true);
		
		//Instantiate animation thread
		animThread = new Thread(this);
		//animThread.start();
		
		//oPanel = new JPanel();
		//offscreenGraphics = oPanel.getGraphics();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphics = getGraphics();
	}
	
	public synchronized void run()
	{
		while(animThread != null)
		{
			update(offscreenGraphics);
		}
	
	}
	
	public void update(Graphics g)
	{
		//Clear offscreen graphics context
		g.setColor(Color.BLACK);
		g.clearRect(0,0, 640, 480);
		
		//Draw on the offscreen graphics context
		g.fillPolygon(myPolygon);

		drawPlayer(myPlayer);

		//for(Star star:myStars){
		for(int i = 0; i < myStars.size(); i++){
				System.out.println("MyStar #: "+ myStars.size());   
				try
				{
					if(myStars.get(i)!= null)
					{
						myStars.get(i).drawStar();
						g.drawImage(myStars.get(i).getImage(), myStars.get(i).getX()-10, myStars.get(i).getY()-10, null);
					}
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Removed star was attempted access/n" + e.getMessage());
				}
		}
		
		//Draw the offscreen image to visible graphics context
		graphics.drawImage(offscreenImage, 0, 0, this);	
		
		synchronized(this)
		{
			notifyAll();
		}
	}
	
	public void addNotify()
	{
		super.addNotify();
		
		//Create the offscreen image and graphics context
		offscreenImage = createImage(640, 480);
		offscreenGraphics = offscreenImage.getGraphics();
	}
	
	/**
	 * Set player
	 */
	public void setPlayer(Player player)
	{
		myPlayer = player;
	}
	
	/**
	 * Set stars
	 */
	public void setStars(ArrayList<Star> stars)
	{
		myStars = stars;
	}
	
	/**
	 * Set player
	 */
	public void setPolygon(Polygon p)
	{
		myPolygon = p;
	}
	
	/**
	 * Draws the player
	 * @param player The player object to be drawn
	 */
	public void drawPlayer(Player player){
		
		double tailExtension;
		tailExtension = Math.random() * 10; 
		
		offscreenGraphics.setColor(Color.GREEN);
		offscreenGraphics.fillOval(player.getMainPosition().x-7, player.getMainPosition().y-7, 14, 14);
		offscreenGraphics.drawOval(player.getThrustPosition().x-2, player.getThrustPosition().y-2, 4, 4);
		if(Orbit.key1 == true)
		{	
			offscreenGraphics.drawLine(player.getThrustPosition().x, player.getThrustPosition().y,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
			offscreenGraphics.drawLine(player.getThrustPosition().x-2, player.getThrustPosition().y-2,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
			offscreenGraphics.drawLine(player.getThrustPosition().x+2, player.getThrustPosition().y+2,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
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
