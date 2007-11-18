import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//import Sound.PlayThread;

public class Sound {
	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	Boolean playback;
	
	public Sound()
	{
		playback = false;
	}
	
	public void playAudio(String fileName)
	{
		try
		{
		File mySoundFile = new File(fileName);
		audioInputStream = AudioSystem.getAudioInputStream(mySoundFile);
		audioFormat = audioInputStream.getFormat();
		System.out.println(audioFormat);
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
	
	public void stopAudio()
	{
		playback = false;
	}
	
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
