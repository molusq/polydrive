package network.v1;

import java.util.ArrayList;

/**
 * This class stores information about a post in a social network. 
 * The main part of the post consists of a (possibly multi-line)
 * text message. Other data, such as author and time, are also stored.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1
 */
class MessagePost {
    private final String username;  // username of the post's author
    private final String message;   // an arbitrarily long, multi-line message
    private final long timestamp;
    private int likes;
    private final ArrayList<String> comments;

    /**
     * Constructor for objects of class MessagePost.
     * 
     * @param author    The username of the author of this post.
     * @param text      The text of this post.
     */
    MessagePost(String author, String text) {
        username = author;
        message = text;
        timestamp = System.currentTimeMillis();
        likes = 0;
        comments = new ArrayList<>();
    }

    /**
     * Record one more 'Like' indication from a user.
     */
    private void like() {
        likes++;
    }

    /**
     * Record that a user has withdrawn his/her 'Like' vote.
     */
    private void unlike() {
        if (likes > 0) {
            likes--;
        }
    }

    /**
     * Add a comment to this post.
     * 
     * @param text  The new comment to add.
     */
    private void addComment(String text) {
        comments.add(text);
    }

    /**
     * Return the text of this post.
     * 
     * @return The post's text.
     */
    private String getText() {
        return message;
    }

    /**
     * Return the time of creation of this post.
     * 
     * @return The post's creation time, as a system time value.
     */
    private long getTimeStamp() {
        return timestamp;
    }

    /**
     * Display the details of this post.
     * 
     * (Currently: Print to the text terminal. This is simulating display 
     * in a web browser for now.)
     */
    void display() {
        System.out.println(username);
        System.out.println(message);
        System.out.print(timeString(timestamp));
        
        if (likes > 0) {
            System.out.println("  -  " + likes + " people like this.");
        } else {
            System.out.println();
        }
        
        if (comments.isEmpty()) {
            System.out.println("   No comments.");
        } else {
            System.out.println("   " + comments.size() + " comment(s). Click here to view.");
        }
    }
    
    /**
     * Create a string describing a time point in the past in terms 
     * relative to current time, such as "30 seconds ago" or "7 minutes ago".
     * Currently, only seconds and minutes are used for the string.
     * 
     * @param time  The time value to convert (in system milliseconds)
     * @return      A relative time string for the given time
     */
    
    private final String timeString(long time) {
        long current = System.currentTimeMillis();
        long pastMillis = current - time;      // time passed in milliseconds
        long seconds = pastMillis/1000;
        long minutes = seconds/60;
        if (minutes > 0) {
            return minutes + " minutes ago";
        } else {
            return seconds + " seconds ago";
        }
    }
}
