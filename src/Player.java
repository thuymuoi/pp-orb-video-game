public class Player {
	private Position mainPosition;  //Position of main ship
	private Thruster thruster;		//Thruster object for ship
	//private double speed;		//Original speed idea
	private Position speed;
	private double speedMagnitude;
	
	//default constructor
	public Player () {
		//speed = 5;		//Original speed idea
		
		speed = new Position(0,0);
		speedMagnitude = 5;
		mainPosition = new Position (50,150);
		thruster = new Thruster(mainPosition);
	}
	
	public void update(Boolean thrust, Boolean slow){	
		if(thrust){
			//Original speed idea
			//mainPosition.x -= speed*java.lang.Math.cos(thruster.getAngle());
			//mainPosition.y -= speed*java.lang.Math.sin(thruster.getAngle());
		
			speed.x += (int)(speedMagnitude * java.lang.Math.cos(thruster.getAngle()));
			speed.y += (int)(speedMagnitude * java.lang.Math.sin(thruster.getAngle()));
			
			mainPosition.x -= speed.x;
			mainPosition.y -= speed.y;
			thruster.updatePosition(mainPosition);
		}
		else {
			mainPosition.x -= speed.x;
			mainPosition.y -= speed.y;
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
			distance = 15;
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
			angle += 0.2;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}
		
	}//end Thruster class


}
