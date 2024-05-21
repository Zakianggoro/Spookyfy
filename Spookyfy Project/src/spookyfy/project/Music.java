package spookyfy.project;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public abstract class Music {
    private final String title;
    private final String path;
    private String genre;
    private int year;
    private String artist;
    private String composer;

    public Music(String title,String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getArtist() {
        return artist;
    }

    public String getComposer() {
        return composer;
    }
    public String getPath() {
        return this.path;
    }

    // Abstract method to be implemented by subclasses
    public abstract void play();

    // Method to stop playing music (optional)
    public void stop() {
        // Implementation to stop playing music
    }

    // Helper method to load audio file
    protected AudioInputStream loadAudio(String filePath) throws Exception {
        return AudioSystem.getAudioInputStream(new File(filePath));
    }

    // Helper method to play audio from AudioInputStream
    protected void playAudio(AudioInputStream audioInputStream) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // Sleep to allow Clip to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000); // Convert microseconds to milliseconds
        } catch (IOException | InterruptedException | LineUnavailableException e) {
        }
    }
}
