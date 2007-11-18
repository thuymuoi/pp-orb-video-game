import java.lang.*;

public class Line {
	private DPosition startP;
	private DPosition endP;
	
	public Line(){
		startP = new DPosition();
		endP = new DPosition();
	}
	
	public Line (DPosition startP, DPosition endP){
		this.startP = startP;
		this.endP = endP;
	}

	public DPosition getStartP(){
		return startP;
	}
	
	public DPosition getEndP(){
		return endP;
	}
	
	public double length () {
		return Math.sqrt(Math.pow(startP.x - endP.x,2) + Math.pow(startP.y - endP.y,2));
	}
}
