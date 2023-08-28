package override.offthewall;

/**
 * @author Florian Latapie
 */
public class Sub extends Souper {
    private final int numb = 17;

    @Override
    void souperAndSub() {
        System.out.println("Sub#souperAndSub numb = " + numb);
    }
}
