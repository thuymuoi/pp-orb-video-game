import java.lang.Math;


public class Player {
	public Position position;
	public Thruster thruster;
	
	//default constructor
	public Player () {
		position = new Position (50,150);
		thruster = new Thruster(position);
	}
	
	public void update(Boolean thrust, Boolean slow){	
		if(thrust){
		}
		else {
			
		}
			
	}
	
	public class Thruster {
		Position tPos;
		double angle;
		double distance;
		
		//default constructor
		public Thruster(Position orbit){
			angle = 0;
			tPos = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));
			
		}
		
		public double getAngle(){
			return angle;
		}
		
		public void updatePosition(Position orbit){
			
						
		}
		
		
	}//end Thruster class


}
