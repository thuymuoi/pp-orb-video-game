import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MapLoader {
	ArrayList lineList = new ArrayList();
	Graphics graphics;
	public MapLoader(GameWindow gameWindow) {
		graphics = gameWindow.getGraphics();
	}

	public ArrayList<Line> readFile(String filename) throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(filename));
		lineList = new ArrayList();
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
				lineList.add(new Line(new DPosition((oldx+120)*5,(oldy+205)*2.3),new DPosition((x+120)*5,(y+205)*2.3)));
			}
			oldx = x;
			oldy = y;
		}
		System.out.println("Size: " + lineList.size());

		input.close();
		return lineList;
	}
	
	public ArrayList<Star> generateStars(String file){
		if(file == "src/map.txt"){
			ArrayList<Star> starList = new ArrayList<Star>();
			starList.add(new Star(graphics, 410,400));
			starList.add(new Star(graphics, 200,220));
			starList.add(new Star(graphics, 320,130));
			return starList;
		}
		else if(file == "src/map2.txt"){
			ArrayList<Star> starList = new ArrayList<Star>();
			starList.add(new Star(graphics, 410,400));
			starList.add(new Star(graphics, 100,120));
			starList.add(new Star(graphics, 320,130));
			return starList;
		}
		return null;
	}
}