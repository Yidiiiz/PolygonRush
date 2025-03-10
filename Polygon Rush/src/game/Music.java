package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {

	private Clip backgroundMusicClip;

	// Constructor for Music class
	public Music() {
		backgroundMusicClip = null;
	}

	private void playMusic(String trackFilePath) {
		try {
			if (backgroundMusicClip != null) {
				// Stop any currently playing music
				stopBackgroundMusic();
			}

			// Load the audio file and open it into the clip
			File musicFile = new File(trackFilePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
			backgroundMusicClip = AudioSystem.getClip();
			backgroundMusicClip.open(audioStream);

			// Start playing the clip in a loop
			backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.err.println("Error playing background music: " + e.getMessage());
		}
	}

	public void stopBackgroundMusic() {
		if (backgroundMusicClip != null) {
			backgroundMusicClip.stop();
		}
	}

	public void playLevelOneMusic() {
		playMusic("resources/music/Level_One.wav");
	}

	// Play the music for Level 2
	public void playLevelTwoMusic() {
		playMusic("resources/music/Level_Two.wav");
	}

	// Play the music for Level 3
	public void playLevelThreeMusic() {
		playMusic("resources/music/Level_Three.wav");
	}



}
