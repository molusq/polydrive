package network.v2;

import java.util.ArrayList;

/**
 * This class stores information about a post in a social network news feed. 
 * The main part of the post consists of a (possibly multi-line)
 * text message. Other data, such as author and time, are also stored.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.2
 */
class MessagePost extends Post {
    private final String message;  // an arbitrarily long, multi-line message

    /**
     * Constructor for objects of class MessagePost.
     * 
     * @param author    The username of the author of this post.
     * @param text      The text of this post.
     */
    private MessagePost(String author, String text) {
        super(author);
        message = text;
    }

    /**
     * Return the text of this post.
     * 
     * @return The post's message text.
     */
    private String getText() {
        return message;
    }
}
