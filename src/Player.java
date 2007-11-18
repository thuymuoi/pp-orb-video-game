public class Player {
	private Position mainPosition;  //Position of main ship
	private Thruster thruster;		//Thruster object for ship
	private Position speed;
	private double speedMagnitude;
	private double radius;
	private int	maxSpeed;
	private int counter;
	private int health = 0;
	private boolean alive;
	//asdf
	
	//default constructor
	public Player () {
		speed = new Position(0,0);
		speedMagnitude = 2;
		maxSpeed = 2;
		
		mainPosition = new Position (100,150);
		thruster = new Thruster(mainPosition);
		
		radius = 14;
		counter = 0;
	}
	
	
	public void update(Boolean thrust, Boolean slow){	
		if(thrust){
			if((counter % 3)==0){
				speed.x += (int)(speedMagnitude * java.lang.Math.cos(thruster.getAngle()));
				speed.y += (int)(speedMagnitude * java.lang.Math.sin(thruster.getAngle()));
			}
			
			if(speed.x > maxSpeed){
				speed.x = maxSpeed;	
			}
			else if(speed.x < maxSpeed*(-1)){
				speed.x = maxSpeed*-1;
			}
			if(speed.y > maxSpeed){
				speed.y = maxSpeed;
			}
			else if(speed.y < maxSpeed*(-1)){
				speed.y = maxSpeed*-1;
			}
		
			if(slow){
				mainPosition.x -= Math.ceil(speed.x/2);
				mainPosition.y -= Math.ceil(speed.y/2);
			}
			else {
				mainPosition.x -= speed.x;
				mainPosition.y -= speed.y;
			}
			thruster.updatePosition(mainPosition);
		}
		else {
			if(slow){
				mainPosition.x -= speed.x/2;
				mainPosition.y -= speed.y/2;
			}
			else {
				mainPosition.x -= speed.x;
				mainPosition.y -= speed.y;
			}
			thruster.updateSlowAnglePosition(mainPosition);
		}
		
		/*
		if(slow){
			mainPosition = new Position (100,150);
			
		}
		*/
		/*
		if(slow){
			speed.x = java.lang.Math.round(((float)speed.x)/2);
			speed.y = java.lang.Math.round(((float)speed.y)/2);
		}
		*/
		counter++;	
	}
	
	public void setMainPosition(int x, int y){
		mainPosition = new Position(x,y);
	}
	
	public void setSpeedX(int newSpeed){
		speed.x = newSpeed;
	}
	
	public void setSpeedY(int newSpeed){
		speed.y = newSpeed;
	}
	
	public Position getSpeed(){
		return speed;
	}
	
	public Position getMainPosition(){
		return mainPosition;		
	}
	
	public double getRadius(){
		return radius;
	}
	
	public int getHealth(){
		return health;
	}
	
	public Position getThrustPosition(){
		return thruster.getPosition();	
	}

	public double getThrusterAngle(){
		return thruster.getAngle();
	}
	
	public void changeHealth(int change){
		//For damages.  Checking if the damage is greater than the shields.
		if (change < 0 && -change <= health){
			health += change;
		}
		//If damage is greater than shields, then take remainder off of health.
		else if (change < 0 && -change > health){
			alive = false;
			health = 0;
		}
		
		//For health.
		if (change > 0 && health < 100){
			health += change;
			if (health > 100) {
				health = 100;
			}
		}
	}
	
	private class Thruster {
		private Position thrustPosition;
		private double angle;
		private double angleChange;
		private double distance;
		
		//default constructor
		public Thruster(Position orbit){
			angle = 0;
			angleChange = 0.35;
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

	public void die(){
		System.out.println("GAME OVER");
		setMainPosition(100,150); //hack to simulate dieing
		changeHealth(100);
	}
}
