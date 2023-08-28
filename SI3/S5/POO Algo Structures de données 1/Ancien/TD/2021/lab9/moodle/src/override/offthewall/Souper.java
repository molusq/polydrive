package override.offthewall;

/**
 * @author Florian Latapie
 */
public class Souper {
    private final int numb = 42;

    void souperAndSub() {
        System.out.println("Souper#souperAndSub numb = " + numb);
    }

    void souper() {
        System.out.println("Souper#souper");
        souperAndSub();
    }
}
