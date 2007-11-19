import java.awt.Graphics;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MapLoader {
	ArrayList lineList = new ArrayList();
	Graphics graphics;
	Polygon polygon;
	ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	
	public MapLoader(GameWindow gameWindow) {
		graphics = gameWindow.getGraphics();
	}
	/**
	 * 
	 * @param filename for the map to be loaded
	 * @return an ArrayList of lines comprising the map
	 * @throws IOException
	 * This function loads in a map file and creates a ArrayList of lines to be returned, and also generates a polygon which it stores internally.
	 */
	public ArrayList<Line> readFile(String filename) throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(filename));
		lineList = new ArrayList();
		polygon = new Polygon();
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
			
			
			polygon.addPoint((int)((x+120)*5), (int)((y+205)*2.3));
		}
		System.out.println("Size: " + lineList.size());
		
		polygons.add(polygon);
		input.close();
		return lineList;
	}
	/**
	 * 
	 * @param The map filename
	 * @return A list of star objects for the appropriate map file. 
	 */
	public ArrayList<Star> generateStars(String file){
		if(file == "src/map.txt"){
			ArrayList<Star> starList = new ArrayList<Star>();
			starList.add(new Star(graphics, 410,400));
			starList.add(new Star(graphics, 200,220));
			starList.add(new Star(graphics, 340,145));
			return starList;
		}
		else if(file == "src/map2.txt"){
			ArrayList<Star> starList = new ArrayList<Star>();
			starList.add(new Star(graphics, 410,400));
			starList.add(new Star(graphics, 150,180));
			starList.add(new Star(graphics, 260,230));
			starList.add(new Star(graphics, 410,320));
			starList.add(new Star(graphics, 390,150));
			starList.add(new Star(graphics, 470,167));
			return starList;
		}
		else if(file == "src/map3.txt"){
			ArrayList<Star> starList = new ArrayList<Star>();
			starList.add(new Star(graphics, 410,400));
			starList.add(new Star(graphics, 150,180));
			starList.add(new Star(graphics, 260,230));
			starList.add(new Star(graphics, 410,320));
			starList.add(new Star(graphics, 390,150));
			starList.add(new Star(graphics, 470,167));
			starList.add(new Star(graphics, 140,420));
			starList.add(new Star(graphics, 120,320));
			starList.add(new Star(graphics, 210,400));
			return starList;
		}
		return null;
	}
	
	/**
	 * 
	 * @param currentLevel
	 * @return The appropriate polygon object representing the current level
	 */
	public Polygon getPolygon(int currentLevel) {
		return polygons.get(currentLevel);
	}
}