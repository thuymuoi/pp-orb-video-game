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
 * Class Double Position take the position as type double
 * @author Thuy Truong
 * University of Southern California
 * Code for a Cause 2007
 */
public class DPosition {
	double x;
	double y;
	
	public DPosition(){
		x = 0.0;
		y = 0.0;
	}
	
	public DPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
}
