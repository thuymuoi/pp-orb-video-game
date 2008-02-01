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


import java.io.*;
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;

/**The Star class creates stars that can be collected.  It takes in a prerendered image of a star as a
 * buffered image, which is then converted into a graphics object.  It requires two int inputs, x and y.
 * 
 * @author James Myoung
 *
 */
public class Star {
	Graphics graphics;
	private BufferedImage bi;
	private int x;
	private int y;
	
	/**Constructor.  Creates a graphics instance of a star with the specified x and y coordinates.
	 * 
	 * @param graphics
	 * @param x
	 * @param y
	 */
	public Star(Graphics graphics, int x, int y){
		this.graphics = graphics;
		bi = null;
		this.x = x;
		this.y = y;
	}
	
	/**Draws a star at the specified coordinates
	 * 
	 */
	public void drawStar(){
		try {
			bi = ImageIO.read(new File("src/Star.gif")); 
		} catch (IOException e){
			System.out.println("asdf");
		}
        graphics.drawImage(bi, x-10, y-10, null);
	}

	/**Gets the x coordinate
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**Sets the x coordinate
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**Gets the y coordinate
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**Sets the y coordinate
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
