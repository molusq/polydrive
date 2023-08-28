package nihaoshijie;

/**
 * The traditional start point for learning any (computer) language. Note that
 * this class does not have to be named Main. But to be executable by the
 * JVM it must have a method main, as below.
 * 
 * The follwing tag is obligatory for all your classes to indicate who owns
 * the code.
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 * If you modify existing code, add another @author tag with your name, eg,
 * @author Florian LAtapie
 */
class Main {
    /**
     * Entry point for the JVM to begin executing code. Note that this method
     * must have exactly this signature, with the following exception:
     *   main(String... args)
     * also works. Trust me - details will be supplied later....
     * 
     * @param args
     *            Unused.
     */
    public static void main(String[] args) {
        System.out.println("Ni hao shijie");
        NiHaoShijie nh = new NiHaoShijie();
        nh.print();

        System.out.print(nh.toString());

        nh = new NiHaoShijie("Yo");
        System.out.print(nh.toString());
    }
}