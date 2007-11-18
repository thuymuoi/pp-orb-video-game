public class CollisionDetection {
	
	Player player;
	
	public CollisionDetection(Player p){
		player = p;
	}
	
	public boolean collideWithLine (Line line){
		double a = 0;
		double hypotenuse = 0;
		double distance = 0;
		Line hypo = new Line(line.getEndP(), new DPosition(player.getMainPosition().x.doubleValue(), player.getMainPosition().y.doubleValue()));
		
		a = line.length();
		hypotenuse = hypo.length();
		distance = Math.sqrt(Math.pow(hypotenuse,2)- Math.pow(a,2)/4);
		
		if (distance > player.getRadius())//make sure we have radius for player
			return false;
		else {
			//Implement player bouncing
			DPosition wallVector = new DPosition((line.getStartP().x - line.getEndP().x)/line.length(), (line.getStartP().y - line.getEndP().y)/line.length());
			DPosition wallNormal = new DPosition((line.getStartP().x /2 - (double)player.getMainPosition().x)/length(line.getStartP(), player.getMainPosition()), (line.getStartP().y /2 - (double)player.getMainPosition().y)/length(line.getStartP(), player.getMainPosition()));
			
			double t = dotProduct(wallVector, player.getMainPosition());
			double n = dotProduct(wallNormal, player.getMainPosition());
	
			DPosition vt = new DPosition(wallVector.x * t, wallVector.y * t);
			DPosition vn = new DPosition(wallNormal.x * (-n), wallNormal.y * (-n));
			
			player.setSpeedX((int)Math.round(dotProduct(new DPosition(1,0), vn) + dotProduct(new DPosition(1,0), vt)));
			player.setSpeedY((int)Math.round(dotProduct(new DPosition(0,1), vn) + dotProduct(new DPosition(0,1), vt)));
			return true;	
			
		}
	}
	
	public double dotProduct(DPosition vec1, DPosition vec2){
		return vec1.x * vec2.x + vec1.y * vec2.y;
	}
	
	public double dotProduct(DPosition vec1, Position vec2){
		return vec1.x * (double)vec2.x + vec1.y * (double)vec2.y;
	}
	
	public double length (DPosition startP, DPosition endP) {
		return Math.sqrt(Math.pow(startP.x - endP.x,2) + Math.pow(startP.y - endP.y,2));
	}
	
	public double length (DPosition startP, Position endP) {
		return Math.sqrt(Math.pow(startP.x - (double)endP.x,2) + Math.pow(startP.y - (double)endP.y,2));
	}
	
	/*
	 * object center: c
object speed vector: speedv
line ends points: pt1, pt2

line unit vector: wallVec = (pt2 - pt1)/(length(pt1,pt2));

line normal vector: wallNormal =  t/2 - c

t: wallVec *dot-product* speedv
n: wallNormal *dot-product* speedv

vt: wallVec *cross-product* t
vn: wallNormal *cross-product* (-n)

speedv = new Vector(1, 0).dot(vn) + new Vector(1, 0).dot(vt);
speedv = new Vector(0, 1).dot(vn) + new Vector(0, 1).dot(vt);

	 * 
	public double bouncingAngle (Position a1 , Position a2, Position b, double radius, double angle ){
		double a = 0;
		double hypotenuse = 0;
		double distance = 0;
		
		a = length(a1,a2);
		hypotenuse = length(a1,b);
		distance = Math.sqrt(Math.pow(hypotenuse,2)- Math.pow(a,2));
		
		
		double incomingAngle = 0;
		double changeAngle = 0 ;
		changeAngle = Math.atan((a2.y - a1.y)/(a2.x - a1.x));
		
		
		
		if (distance > radius)
			return 0;
		else
			return 0;
	}
	*/
}
