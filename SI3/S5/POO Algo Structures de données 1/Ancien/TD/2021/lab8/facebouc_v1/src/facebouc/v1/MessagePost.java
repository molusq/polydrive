package facebouc.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about a post in a social network. The main part
 * of the post consists of a (possibly multi-line) text message. Other data,
 * such as author and time, are also stored.
 *
 * @author Michael Kölling and David J. Barnes
 * @author Peter Sander
 * @author Florian Latapie
 */
class MessagePost {
    private final String username; // username of the post's author
    private final String message; // an arbitrarily long, multi-line message
    private final long timestamp;
    private int likes;
    private final List<String> comments;

    /**
     * Constructor for objects of class MessagePost.
     *
     * @param author The username of the author of this post.
     * @param text   The text of this post.
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
    void like() {
        likes++;
    }

    /**
     * Record that a user has withdrawn his/her 'Like' vote.
     */
    void unlike() {
        if (likes > 0) {
            likes--;
        }
    }

    /**
     * Add a comment to this post.
     *
     * @param text The new comment to add.
     */
    void addComment(String text) {
        comments.add(text);
    }

    /**
     * Return the text of this post.
     *
     * @return The post's text.
     */
    String getText() {
        return message;
    }

    /**
     * Return the time of creation of this post.
     *
     * @return The post's creation time, as a system time value.
     */
    long getTimeStamp() {
        return timestamp;
    }

    /**
     * Display the details of this post as follows:
     * <p>
     * username
     * message
     * timestamp<<likes>>
     * <<comments>>
     * <p>
     * where
     * <<likes>> is
     * "  -  <<N>> people like this"
     * if there are N likes (the number of likes replaces <<N>>); otherwise
     * nothing
     * <<comments>> is
     * "   <<M>> comment(s). Click here to view."
     * if there are M comments; otherwise
     * "   No comments."
     * if there are no comments
     * <p>
     * Eg,
     * Leonardo da Vinci
     * Code this...!
     * 0 seconds ago  -  2 people like this.
     * No comments.
     * or
     * Taylor Twit
     * Party Like It's 1989
     * (c)1989 Taylor Twit
     * 30 seconds ago
     * 12345 comment(s). Click here to view.
     * Pay careful attention to the formatting (whitespace).
     * <p>
     * (Currently: Print to the text terminal. This is simulating display in a
     * web browser for now.)
     */
    void display() {
        System.out.println(this.username);
        System.out.println(this.message);

        if (this.likes > 0) {
            System.out.println(timeString(this.timestamp) + "  -  " + this.likes + " people like this.");
        } else {
            System.out.println(timeString(this.timestamp));
        }

        if (this.comments.isEmpty()) {
            System.out.println("   No comments.");
        } else {
            System.out.println("   " + this.comments.size() + " comment(s). Click here to view.");
        }

    }

    /**
     * Create a string describing a time point in the past in terms relative to
     * current time, such as "30 seconds ago" or "7 minutes ago". Currently,
     * only seconds and minutes are used for the string.
     *
     * @param time The time value to convert (in system milliseconds)
     * @return A relative time string for the given time
     */

    private final String timeString(long time) {
        long current = System.currentTimeMillis();
        long pastMillis = current - time; // time passed in milliseconds
        long seconds = pastMillis / 1000;
        long minutes = seconds / 60;
        if (minutes > 0) {
            return minutes + " minutes ago";
        } else {
            return seconds + " seconds ago";
        }
    }
}
