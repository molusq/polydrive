package nihaoshijie;

/**
 * Class to create an object to print a message.
 * 
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 */
class NiHaoShijie {
    private String message = "Ni hao shijie";

    /**
     * Must use the variable message.
     * @return the character string: "from NiHaoShijie: Ni hao shijie"
     */
    public String toString() {
        return "from NiHaoShijie: " + message;
    }

    /**
     * Prints the character string returned by the method toString
     */
    void print() {
        System.out.println(toString());
    }
}
