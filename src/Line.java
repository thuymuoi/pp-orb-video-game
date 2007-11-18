import java.lang.*;

public class Line {
	private Position startP;
	private Position endP;
	
	public Line(){
		startP = new Position();
		endP = new Position();
	}
	
	public boolean collide (Position a1, Position a2, Position b){
		double a = 0;
		double hypotenuse = 0;
		double height = 0;
		
	
		
		
		return false;
	}
	
	public double length (Position a, Position b) {
		return Math.sqrt(Math.pow((double)b.x - (double)a.x,2) + Math.pow((double)b.y - (double)a.y,2));
	}
	
}
