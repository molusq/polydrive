package techsupport.v1;

/**
 * The responder class represents a response generator object. It is used to
 * generate an automatic response to an input string.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1 (2011.07.31)
 */
public class Responder {
    /**
     * Construct a Responder - nothing to do
     */
    public Responder() {
    }

    /**
     * Generate a response.
     * 
     * @return A string that should be displayed as the response
     */
    public String generateResponse() {
        return "That sounds interesting. Tell me more...";
    }
}
