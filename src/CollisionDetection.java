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
			
			return true;	
		}
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
