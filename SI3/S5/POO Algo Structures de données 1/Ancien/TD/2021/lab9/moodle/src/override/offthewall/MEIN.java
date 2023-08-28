package override.offthewall;

/**
 * @author Florian Latapie
 */
public class MEIN {
    public static void main(String... args) {
        Souper s = new Souper();
        s.souper();
        s = new Sub();
        s.souper();
    }
}
