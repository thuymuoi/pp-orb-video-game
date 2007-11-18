import java.io.*;


public class MapLoader {
	public void readFile() throws IOException
	{
		BufferedReader input = new BufferedReader(new FileReader("src/map.txt"));
	
	String value;
	while((input.readLine() != null)){
		
		System.out.print(input.readLine() + " ");
	}
		
	
	input.close();
}

}