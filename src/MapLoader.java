import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MapLoader {
	ArrayList lineList = new ArrayList();
	public void readFile() throws IOException
	{
		BufferedReader input = new BufferedReader(new FileReader("src/map.txt"));
		double x;
		double y;
		double oldx=-96822.96822;
		double oldy=-96822.96822;
		while(true){
			String line = input.readLine();
			
			if(line == null){
				break;
			}
			String[] parts = line.split(" ");
			x = Double.valueOf(parts[1].trim());
			y = Double.valueOf(parts[2].trim());
			if(oldx != -96822.96822 && oldy != -96822.96822){
				System.out.println("x:" + (x+116) + " y:" + (y+175)+"oldx:" + (oldx+116) + " oldy:" + (oldy+175));
				lineList.add(new Line(new DPosition(oldx,oldy),new DPosition(x,y)));
			}
			oldx = x;
			oldy = y;
		}
		System.out.println("Size: " + lineList.size());
	
	input.close();
}

}