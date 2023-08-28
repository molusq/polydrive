package facebouc.v1;

import java.util.ArrayList;

/**
 * The NewsFeed class stores news posts for the news feed in a social network
 * application (like FaceBook or Google+).
 * 
 * Display of the posts is currently simulated by printing the details to the
 * terminal. (Later, this should display in a browser.)
 * 
 * This version does not save the data to disk, and it does not provide any
 * search or ordering functions.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1
 */
class NewsFeed {
    private final ArrayList<MessagePost> messages;
    private final ArrayList<PhotoPost> photos;

    /**
     * Construct an empty news feed.
     */
    NewsFeed() {
        messages = new ArrayList<>();
        photos = new ArrayList<>();
    }

    /**
     * Add a text post to the news feed.
     * 
     * @param text
     *            The text post to be added.
     */
    void addMessagePost(MessagePost message) {
        messages.add(message);
    }

    /**
     * Add a photo post to the news feed.
     * 
     * @param photo
     *            The photo post to be added.
     */
    void addPhotoPost(PhotoPost photo) {
        photos.add(photo);
    }

    /**
     * Show the news feed. Currently: print the news feed details to the
     * terminal. (To do: replace this later with display in web browser.)
     */
    void show() {
        // display all text posts
        for (MessagePost message : messages) {
            message.display();
            System.out.println(); // empty line between posts
        }

        // display all photos
        // for (PhotoPost photo : photos) {
        //     photo.display();
        //     System.out.println(); // empty line between posts
        // }
        photos.forEach(
                photo -> {photo.display();
                          System.out.println();
                         });
    }
}
