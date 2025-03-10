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
        playMusic("resources/music/Level_one.wav");
    }

    // Play the music for Level 2
    public void playLevelTwoMusic() {
        playMusic("resources/music/Level_Two.wav");
    }

    // Play the music for Level 3
    public void playLevelThreeMusic() {
        playMusic("resources/music/Level_Three.wav");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public void playBackgroundMusic(String musicFilePath) {
//        try {
//            // Stop any previously playing music before starting a new one
//            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
//                backgroundMusicClip.stop();
//            }
//
//            // Load and play new music
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(musicFilePath));
//            backgroundMusicClip = AudioSystem.getClip();
//            backgroundMusicClip.open(audioInputStream);
//            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the music
//            backgroundMusicClip.start();
//        } catch (Exception e) {
//            System.err.println("Error playing background music: " + e.getMessage());
//        }
//    }
//
//    public void stopBackgroundMusic() {
//        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
//            backgroundMusicClip.stop();
//        }
//    }
    
    
    
    
    
//    // Play background music
//    public void playBackgroundMusic(String filePath) {
//        try {
//            // Load the music file and create a Clip
//            File musicFile = new File(filePath);
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
//            
//            // If there is a previous clip, stop it first
//            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
//                backgroundMusicClip.stop();
//            }
//            
//            backgroundMusicClip = AudioSystem.getClip();
//            backgroundMusicClip.open(audioStream);
//           
//            
//            // Start the music
//            backgroundMusicClip.start();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();  // Handle any potential errors
//        }
//    }
//
//    // Stop background music
//    public void stopBackgroundMusic() {
// 
//            backgroundMusicClip.close(); 
//        
//    }

   

    
}
