package addressbook.v1t;

/**
 * Thrown if a key is already in use.
 * @author Peter Sander
 * @author F
 */
@SuppressWarnings("serial")
public class DuplicateKeyException extends RuntimeException {

    /**
     * Constructor. Stores message with superclass.
     * @param messge
     */
	public DuplicateKeyException(String message){
		super(message);
	}
	
	@Override
	public String toString() {
		return "Key already in use: " + getMessage() + ".";
	}
}