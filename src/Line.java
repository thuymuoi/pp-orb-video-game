import java.lang.*;

public class Line {
	private Position startP;
	private Position endP;
	
	public Line(){
		startP = new Position();
		endP = new Position();
	}
	
	public boolean collide (Position a1, Position a2, Position b, double radius){
		double a = 0;
		double hypotenuse = 0;
		double distance = 0;
		
		a = length(a1,a2);
		hypotenuse = length(a1,b);
		distance = Math.sqrt(Math.pow(hypotenuse,2)- Math.pow(a,2));
		
		if (distance > radius)
			return false;
		else
			return true;
	}
	
	public double length (Position a, Position b) {
		return Math.sqrt(Math.pow((double)b.x - (double)a.x,2) + Math.pow((double)b.y - (double)a.y,2));
	}
	
}
