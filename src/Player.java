public class Player {
	private Position mainPosition;  //Position of main ship
	private Thruster thruster;		//Thruster object for ship
	private Position speed;
	private double speedMagnitude;
	private int	maxSpeed;
	
	//default constructor
	public Player () {
		speed = new Position(0,0);
		speedMagnitude = 2;
		maxSpeed = 1;
		
		mainPosition = new Position (50,150);
		thruster = new Thruster(mainPosition);
	}
	
	public void update(Boolean thrust, Boolean slow){	
		if(thrust){
			speed.x += (int)(speedMagnitude * java.lang.Math.cos(thruster.getAngle()));
			speed.y += (int)(speedMagnitude * java.lang.Math.sin(thruster.getAngle()));
			if(speed.x > maxSpeed){
				speed.x = maxSpeed;	
			}
			if(speed.y > maxSpeed){
				speed.y = maxSpeed;
			}
		
			mainPosition.x -= speed.x;
			mainPosition.y -= speed.y;
			thruster.updatePosition(mainPosition);
		}
		else {
			mainPosition.x -= speed.x;
			mainPosition.y -= speed.y;
			thruster.updateSlowAnglePosition(mainPosition);
		}
		/*
		if(slow){
			speed.x = java.lang.Math.round(((float)speed.x)/2);
			speed.y = java.lang.Math.round(((float)speed.y)/2);
		}
		*/
			
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
		private double angleChange;
		private double distance;
		
		//default constructor
		public Thruster(Position orbit){
			angle = 0;
			angleChange = 0.2;
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
			angle += angleChange;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}
		
		public void updateSlowAnglePosition(Position orbit){
			angle += angleChange/2;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}
		
	}//end Thruster class


}
