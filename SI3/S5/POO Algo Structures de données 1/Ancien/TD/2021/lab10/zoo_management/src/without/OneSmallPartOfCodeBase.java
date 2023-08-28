package without;


import java.util.List;

/**
 * On small part of a massive animal management code base.
 * @author (not) Peter Sander
 * @author Florian Latapie
 */
class OneSmallPartOfCodeBase {
    private Zoo zoo;

    /**
     * Constructor.
     * Sets up a zoo containing different species of animal.
     */
    OneSmallPartOfCodeBase() {
        zoo = new Zoo(List.of(
                new Animal[]{
                        new Walrus(),
                        new Bear(),
                        new Roadrunner()
                }));
    }

    /**
     * @return Information about all the animals in the zoo.
     */
    @Override
    public String toString() {
        return zoo.toString();
    }

    /**
     * Runs things.
     */
    public static void main(String... args) {
        System.out.println(new OneSmallPartOfCodeBase());
    }
}
