package addressbook.v1t;

public class NoMatchingDetailsException extends RuntimeException {
    /**
     * Constructor. Stores message with superclass.
     * @param messge
     */
	public NoMatchingDetailsException(String message){
		super(message);
	}

    @Override
    public String toString() {
        return "No details matching: " + getMessage() + " were found.";
    }
}