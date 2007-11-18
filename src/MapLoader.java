import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MapLoader {
	public void readFile() throws IOException
	{
		BufferedReader input = new BufferedReader(new FileReader("src/map.txt"));
		while(true){
			String line = input.readLine();
			
			if(line == null){
				break;
			}
			String[] parts = line.split(" ");
			double x = Double.valueOf(parts[1].trim());
			double y = Double.valueOf(parts[2].trim());
			//double newInput = input.read();
			System.out.println("x:" + (x+116) + " y:" + (y+175));
		}

	
	input.close();
}

}