
/** Player Class: contains all the game mechanics for
 * the orb object, including its thrusters.
 *
 */
public class Player {
	private Position mainPosition;  //Position of main ship
	private Thruster thruster;		//Thruster object for ship
	private Position speed;			//Velocity vector
	private double speedMagnitude;	//Magnitude of the velocity
	private double radius;			//Radius of the bounding circle of the player orb object
	private int	maxSpeed;			//Maximum velocity
	private int counter;			//Counter to reduce the number of accelerations
	private int health = 0;			//Health value for the player
	private boolean alive;			//Whether the player is alive or not

	
	/** Constructor of the player class. 
	 *  Initializes the speed values, main position,
	 *  thruster, bounding circle radius, and the counter.
	 */
	public Player () {
		speed = new Position(0,0);
		speedMagnitude = 2;
		maxSpeed = 2;
		
		mainPosition = new Position (100,150);
		thruster = new Thruster(mainPosition);
		
		radius = 14;
		counter = 0;
	}
	
	/** Update the Orb speed and position through the user input.
	 * Orb velocity and position depends on the position of the thruster.
	 * Also updates the thruster position at the same time
	 * @param thrust User input one: tells the thruster to fire (also stop thruster from orbiting)
	 * @param slow User input two: Slow the speed of the environment 
	 */
	public void update(Boolean thrust, Boolean slow){	
		if(thrust){ //Thrust button is enabled
			//Slows down the number of accelerations
			if((counter % 3)==0){	
				//Modify the speed based on the angle of the thruster
				speed.x += (int)(speedMagnitude * java.lang.Math.cos(thruster.getAngle())); 
				speed.y += (int)(speedMagnitude * java.lang.Math.sin(thruster.getAngle()));
			}
			
			//Limit the velocity of the orb
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
		
			//if the slow input is pressed, then slow down the speed of the orb
			if(slow){
				mainPosition.x -= Math.ceil(speed.x/2);
				mainPosition.y -= Math.ceil(speed.y/2);
			}
			else {
				mainPosition.x -= speed.x;
				mainPosition.y -= speed.y;
			}
			
			//Update the position of the thruster based on the position of the orb
			thruster.updatePosition(mainPosition);
		}
		else {
			if(slow){
				mainPosition.x -= speed.x/2;
				mainPosition.y -= speed.y/2;
			}
			else {
				//Keep a momentum going for the player by continuously changing its position
				mainPosition.x -= speed.x;
				mainPosition.y -= speed.y;
			}
			
			//Tell the thruster to orbit around the orb and modify its position based on the orb
			thruster.updateSlowAnglePosition(mainPosition);
		}
		
		counter++;	
	}
	
	/** Set the position of the orb
	 * 
	 * @param x x value of the orb
	 * @param y y value of the orb
	 */
	public void setMainPosition(int x, int y){
		mainPosition = new Position(x,y);
		speed.x = 0;
		speed.y = 0;
	}
	
	/**Set the x velocity of the orb
	 * 
	 * @param newSpeed x velocity
	 */
	public void setSpeedX(int newSpeed){
		speed.x = newSpeed;
	}
	
	/** Set the y velocity of the orb
	 * 
	 * @param newSpeed y velocity
	 */
	public void setSpeedY(int newSpeed){
		speed.y = newSpeed;
	}
	
	/** Get the velocity of the orb
	 * 
	 * @return speed of the orb
	 */
	public Position getSpeed(){
		return speed;
	}
	
	/** Get the position of the orb
	 * 
	 * @return position of the orb
	 */
	public Position getMainPosition(){
		return mainPosition;		
	}
	
	/** Get the radius of the orb
	 * 
	 * @return radius of the orb
	 */
	public double getRadius(){
		return radius;
	}
	
	/** Get the health of the orb
	 * 
	 * @return health of the orb
	 */
	public int getHealth(){
		return health;
	}
	
	/** Get the thruster position
	 * 
	 * @return position of the thruster position
	 */
	public Position getThrustPosition(){
		return thruster.getPosition();	
	}

	/** Get the angle of the thruster to the orb
	 * 
	 * @return angle of the thruster to the orb
	 */
	public double getThrusterAngle(){
		return thruster.getAngle();
	}
	
	/** Update the health value from collision
	 * 
	 * @param change Value to update the health
	 */
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
	
	/** Thruster class:
	 *  Contains the position of the thruster,
	 *  distance from the orb,
	 *  angle to the orb,
	 *  and the number of radian to change when orbiting around the orb.
	 */
	private class Thruster {
		private Position thrustPosition;	
		private double angle;
		private double angleChange;
		private double distance;
		
		/** Constructor: gets the position of the orb and sets all values of the thruster
		 * 
		 * @param orbit Position of th eorb
		 */
		public Thruster(Position orbit){
			angle = 0;
			angleChange = 0.35;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));
			distance = 15;
		}
		
		/** Get the position of the thruster
		 * 
		 * @return position of the thruster
		 */
		public Position getPosition(){
			return thrustPosition;
		}
		
		/** Get the angle of the thruster to the orb
		 * 
		 * @return angle of the thruster to the orb
		 */
		public double getAngle(){
			return angle;
		}
		
		/** Update the position of the thruster based on the orb
		 * 
		 * @param orbit position of the orb
		 */
		public void updatePosition(Position orbit){
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}

		/** Orbit the thruster around the orb, and update the position of the thruster based on the orb 
		 * 
		 * @param orbit Position of the orb
		 */
		public void updateAnglePosition(Position orbit){
			angle += angleChange;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}
		
		/** Orbit the thruster around the orb, and update the position of the thruster based on the orb 
		 * 
		 * @param orbit Position of the orb
		 */
		public void updateSlowAnglePosition(Position orbit){
			angle += angleChange/2;
			thrustPosition = new Position((int)(orbit.x + distance* java.lang.Math.cos(angle)),(int)(orbit.y + distance*java.lang.Math.sin(angle)));					
		}
		
	}//end Thruster class

	public void die(){
		System.out.println("GAME OVER");
		setMainPosition(110,180); //hack to simulate dieing
		changeHealth(100);
	}
}
