import java.lang.Math.*;


public class Player {
	private Position mainPosition;
	private Thruster thruster;
	private double speed;
	
	//default constructor
	public Player () {
		speed = 5;
		mainPosition = new Position (50,150);
		thruster = new Thruster(mainPosition);
	}
	
	public void update(Boolean thrust, Boolean slow){	
		if(thrust){
			mainPosition.x += speed*java.lang.Math.cos(thruster.getAngle());
			mainPosition.y += speed*java.lang.Math.sin(thruster.getAngle());
			thruster.updatePosition(mainPosition);
		}
		else {
			thruster.updateAnglePosition(mainPosition);
		}
			
	}
	
	public Position getMainPosition(){
		return mainPosition;		
	}
	
	public Position getThrustPosition(){
		return thruster.getPosition();	
	}
	
	
	private class Thruster {
		private Position thrustPosition;
		private double angle;
		private double distance;
		
		//default constructor
		public Thruster(Position orbit){
			angle = 0;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));
		}
		
		public Position getPosition(){
			return thrustPosition;
		}
		
		public double getAngle(){
			return angle;
		}
		
		public void updatePosition(Position orbit){
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}

		public void updateAnglePosition(Position orbit){
			angle += 3;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}
		
	}//end Thruster class


}
