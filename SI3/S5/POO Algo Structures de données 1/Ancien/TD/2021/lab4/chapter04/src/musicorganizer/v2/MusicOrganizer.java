package musicorganizer.v2;

import java.util.ArrayList;

import musicorganizer.MusicPlayer;

/**
 * A class to hold details of audio files.
 * This version can play the files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
class MusicOrganizer {
    // An ArrayList for storing the file names of music files.
    private final ArrayList<String> files;
    // A player for the music files.
    private final MusicPlayer player;
        
    /**
     * Create a MusicOrganizer
     */
    private MusicOrganizer() {
        files = new ArrayList<>();
        player = new MusicPlayer();
    }
    
    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    private void addFile(String filename) {
        files.add(filename);
    }
    
    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    private int getNumberOfFiles() {
        return files.size();
    }
    
    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    private void listFile(int index) {
        if (index >= 0 && index < files.size()) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }
    
    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    private void removeFile(int index) {
        if (index >= 0 && index < files.size()) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    private void startPlaying(int index) {
        String filename = files.get(index);
        player.startPlaying(filename);
    }

    /**
     * Stop the player.
     */
    private void stopPlaying() {
        player.stop();
    }
}
