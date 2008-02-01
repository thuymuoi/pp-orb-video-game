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


/**
 * Collision Detection class
 * Check the player have a collision with an object
 * @author Thuy Truong
 * University of Southern California
 * Code for a Cause 2007
 */

public class CollisionDetection2 {
Player player;
	
	//public constructor
	public CollisionDetection2(Player p){
		player = p;
	}
	
	/** 
	 * Boolean function to detect the collision with the wall
	 * @param line
	 * @return true/false
	 */
	public boolean collideWithLine (Line line){
		double a = 0;
		double hypotenuse = 0;
		double distance = 0;
		double incomingAngle = 0;
		
		//Calculate the distance of the player to one point on the line
		Line hypo = new Line(line.getEndP(), new DPosition(player.getMainPosition().x.doubleValue(), player.getMainPosition().y.doubleValue()));
		hypotenuse = hypo.length();
		
		//Calculate the length of the line
		a = line.length();
		
		//Distance from the player to the line
		distance = Math.sqrt(Math.pow(hypotenuse,2)- Math.pow(a,2)/4);
		
		if (distance > player.getRadius())//make sure we have radius for player
			return false;
		else {
			try {
			//calculate the incoming angle
				incomingAngle = Math.atan(player.getSpeed().x/player.getSpeed().y);
			} catch (ArithmeticException e)
			{
				incomingAngle = Math.PI/2;
			}
			//change the speed of the player according to the new coordinate.
			double newSpeedX = 0;
			double newSpeedY = 0;
			
			newSpeedX = (double)player.getSpeed().x * Math.cos(incomingAngle)+ (double)player.getSpeed().y * Math.sin(incomingAngle);
			newSpeedY = (double)player.getSpeed().x * (-1) * Math.sin(incomingAngle) + (double)player.getSpeed().y * Math.cos(incomingAngle);
			
			//bouncing speed
			double bouncingSpeedX = 0;
			double bouncingSpeedY = 0;
			
			//when it bounce, only the Y speed change the direction, but the X Speed does not change
			bouncingSpeedX = newSpeedX;
			bouncingSpeedY = (-1)* newSpeedY;
			
			//set the new speed of the ball
			player.setSpeedX((int)Math.round(bouncingSpeedX));
			player.setSpeedY((int)Math.round(bouncingSpeedY));
			
			player.update(true,false);
			player.update(true, false);
			
			
			return true;
		}
	}
	
	/**
	 * Boolean function check when the player have a collision with an object at position with certain size
	 * @param pos
	 * @param size
	 * @return true/false
	 */
	public Boolean genericCollision(Position pos, int size){
		if(Math.abs(player.getMainPosition().x - pos.x) < size/2 + player.getRadius() && Math.abs(player.getMainPosition().y - pos.y) < size/2 + player.getRadius() ){
			return true;
		}
		else{
			return false;
		}
	}
			

}