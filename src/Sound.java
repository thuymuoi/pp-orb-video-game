import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * Implements the playing and stopping of sounds
 * @author Owner
 *
 */
public class Sound {
	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	Boolean playback;
	
	/**
	 * Constructor
	 */
	public Sound()
	{
		playback = false;
	}
	
	/**
	 * Plays audio file 
	 * @param fileName Sound file to be played
	 */
	public void playAudio(String fileName)
	{
		try
		{
			File mySoundFile = new File(fileName);
			audioInputStream = AudioSystem.getAudioInputStream(mySoundFile);
			audioFormat = audioInputStream.getFormat();
			
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,audioFormat);
			sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
			playback = true;
			
			new PlayThread().start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Stop playback of audio
	 */
	public void stopAudio()
	{
		playback = false;
	}
	
	/**
	 * Inner class for making a separate thread to run the sound
	 * @author Owner
	 *
	 */
	class PlayThread extends Thread{
		byte tempBuffer[] = new byte[10000];
		
		public void run(){
			try{
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				
				int cnt;
				
				while(((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) && playback)
				{
					if(cnt > 0){
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				
				sourceDataLine.drain();
				sourceDataLine.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
