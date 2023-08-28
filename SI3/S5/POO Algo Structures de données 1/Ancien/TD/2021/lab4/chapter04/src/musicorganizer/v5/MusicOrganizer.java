package musicorganizer.v5;

import java.util.ArrayList;

import musicorganizer.MusicPlayer;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
class MusicOrganizer {
    // An ArrayList for storing music tracks.
    private final ArrayList<Track> tracks;
    // A player for the music tracks.
    private final MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private final TrackReader reader;

    /**
     * Create a MusicOrganizer
     */
    private MusicOrganizer() {
        tracks = new ArrayList<>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("../audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    private void addFile(String filename) {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    private void addTrack(Track track) {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    private void playTrack(int index) {
        if (indexValid(index)) {
            Track track = tracks.get(index);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    private int getNumberOfTracks() {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    private void listTrack(int index) {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    private void listAllTracks() {
        System.out.println("Track listing: ");

        for (Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    private void listByArtist(String artist) {
        for (Track track : tracks) {
            if (track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    private void removeTrack(int index) {
        if (indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    private void playFirst() {
        if (tracks.size() > 0) {
            player.startPlaying(tracks.get(0).getFilename());
        }
    }
    
    /**
     * Stop the player.
     */
    private void stopPlaying() {
        player.stop();
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index) {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if (index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if (index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName) {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for (Track track : tempTracks) {
            addTrack(track);
        }
    }
}
