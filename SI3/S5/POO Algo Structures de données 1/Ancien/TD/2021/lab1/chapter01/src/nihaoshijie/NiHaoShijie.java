package nihaoshijie;
/**
 * Class to create an object to print a message.
 *
 * @author <a href="mailto:sander@univ-cotedazur.fr">Peter Sander</a>
 * @author Florian Latapie
 */
public class NiHaoShijie {
    private String message = "Ni hao shijie";

    /**
     * Default constructor.
     */
    public NiHaoShijie(){
    }

    /**
     * Constructor.
     * Assigns argument value to attribute.
     * @param msg Part of the message to print.
     */
    NiHaoShijie(String msg){
        this.message = msg;
    }
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
    public void print() {
        System.out.println(toString());
    }
}
