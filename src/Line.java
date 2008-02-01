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


import java.lang.*;
/**
 * Class Line
 * including the position of start point and end point of a line
 * the collection of line will create the wall to limit the player
 * @author Thuy Truong
 * University of Southern California
 * Code for a Cause 2007
 */

public class Line {
	/**
	 * Variable Declaration
	 */
	private DPosition startP;
	private DPosition endP;
	
	/**
	 * Default constructor
	 */
	public Line(){
		startP = new DPosition();
		endP = new DPosition();
	}
	
	public Line (DPosition startP, DPosition endP){
		this.startP = startP;
		this.endP = endP;
	}

	/**
	 * Accessor
	 * @return
	 */
	public DPosition getStartP(){
		return startP;
	}
	
	public DPosition getEndP(){
		return endP;
	}
	
	/**
	 * Function to calculate the length of the line from start and end point
	 * @return length;
	 */
	
	public double length () {
		return Math.sqrt(Math.pow(startP.x - endP.x,2) + Math.pow(startP.y - endP.y,2));
	}
}
