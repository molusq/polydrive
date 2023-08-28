package musicorganizer.v3;

import java.util.ArrayList;

import musicorganizer.MusicPlayer;

/**
 * A class to hold details of audio files.
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
        if (validIndex(index)) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }
    
    /**
     * Show a list of all the files in the collection.
     */
    private void listAllFiles() {
        for (String filename : files) {
            System.out.println(filename);
        }
    }
    
    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    private void removeFile(int index) {
        if (validIndex(index)) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    private void startPlaying(int index) {
        if (validIndex(index)) {
            String filename = files.get(index);
            player.startPlaying(filename);
        }
    }

    /**
     * Stop the player.
     */
    private void stopPlaying() {
        player.stop();
    }

    /**
     * Play a file in the collection. Only return once playing has finished.
     * @param index The index of the file to be played.
     */
    private void playAndWait(int index) {
        if (validIndex(index)) {
            String filename = files.get(index);
            player.playSample(filename);
        }
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index) {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if (index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if (index >= files.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}
