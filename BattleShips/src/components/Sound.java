package components;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	private String soundFile;
	private boolean loopFile;
	private float volume;
	Clip clip;
	FloatControl gainControl;
	private boolean muted = false;


	public Sound(String filename, boolean loop, float volume){
		this.soundFile = filename;
		this.loopFile = loop;
		this.volume = volume;
		
		try{
			AudioInputStream s = AudioSystem.getAudioInputStream(new File(audioFile())); 
			AudioFormat f = s.getFormat(); 
			DataLine.Info i = new DataLine.Info(Clip.class , f);
			clip = (Clip) AudioSystem.getLine(i); 
			clip.open(s); 
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-this.volume);
			clip.start();
			playSound(clip);
			 //clip.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(Exception e){
		}
	}
	
	public void muteVolume(){
		this.gainControl.setValue(-80);
		this.muted = true;
	}
	
	public void setVolume(){
		this.gainControl.setValue(-this.volume);
		this.muted = false;
	}
	
	public boolean getMuted(){
		return this.muted;
	}
	
	public String audioFile(){
		String currentDirectory = System.getProperty("user.dir");
		return this.soundFile = currentDirectory +"\\sounds\\" + soundFile;
	}
	
	public void playSound(Clip clip){
		if(this.loopFile == true){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
}
