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
