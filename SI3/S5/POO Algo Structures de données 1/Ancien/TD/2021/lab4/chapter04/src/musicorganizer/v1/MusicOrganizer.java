package musicorganizer.v1;

import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
class MusicOrganizer {
    // An ArrayList for storing the file names of music files.
    private final ArrayList<String> files;
        
    /**
     * Create a MusicOrganizer
     */
    private MusicOrganizer() {
        files = new ArrayList<>();
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
}
