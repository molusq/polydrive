package facebouc.v1;

import java.util.ArrayList;

/**
 * This class stores information about a post in a social network. The main part
 * of the post consists of a photo and a caption. Other data, such as author and
 * time, are also stored.
 * 
 * @author Michael Kölling and David J. Barnes
 * @author Peter Sander
 * @author Florian Latapie
 */
class PhotoPost {
    private final String username; // username of the post's author
    private final String filename; // the name of the image file
    private final String caption; // a one line image caption
    private final long timestamp;
    private int likes;
    private final ArrayList<String> comments;

    /**
     * Constructor for objects of class PhotoPost.
     * 
     * @param author
     *            The username of the author of this post.
     * @param filename
     *            The filename of the image in this post.
     * @param caption
     *            A caption for the image.
     */
    PhotoPost(String author, String filename, String caption) {
        username = author;
        this.filename = filename;
        this.caption = caption;
        timestamp = System.currentTimeMillis();
        likes = 0;
        comments = new ArrayList<String>();
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
     * @param text
     *            The new comment to add.
     */
    void addComment(String text) {
        comments.add(text);
    }

    /**
     * Return the file name of the image in this post.
     * 
     * @return The post's image file name.
     */
    String getImageFile() {
        return filename;
    }

    /**
     * Return the caption of the image of this post.
     * 
     * @return The image's caption.
     */
    String getCaption() {
        return caption;
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
     *
username
  [<<filename>>]
  <<caption>>
timestamp<<likes>>
<<comments>>
     *
     * where
     * <<likes>> is
     * "  -  <<N>> people like this"
     * if there are N likes (the number of likes replaces <<N>>); otherwise
     *  nothing
     * <<comments>> is 
     * "   <<M>> comment(s). Click here to view."
     * if there are M comments; otherwise
     * "   No comments."
     * if there are no comments
     *
     * Eg,
Alexander Graham Bell
  [handset.png]
  Coming soon: the Samsung Galaxy S7 recall.
0 seconds ago  -  4 people like this.
   No comments.

     * Pay careful attention to the formatting (whitespace).
     * 
     * (Currently: Print to the text terminal. This is simulating display in a
     * web browser for now.)
     */
    void display() {
        System.out.println(this.username);
        System.out.println("  ["+this.getImageFile()+"]");
        System.out.println("  "+this.caption);

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
     * @param time
     *            The time value to convert (in system milliseconds)
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
