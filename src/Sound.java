/*
    This file is part of Orb.

    Orb is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Orb is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Orb.  If not, see <http://www.gnu.org/licenses/>. 
    
    This software was developed by members of Project:Possibility, a software 
    collaboration for the disabled.
    
    For more information, visit http://projectpossibility.org
*/


import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * Implements the playing and stopping of sounds
 * @author Charlene Jeune
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
