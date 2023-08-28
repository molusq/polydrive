package without;

import java.util.Arrays;
import java.util.List;

/**
 * On small part of a massive animal management code base.
 * @author (not) Peter Sander
 */
class OneSmallPartOfCodeBase {
    private Zoo zoo;

    /**
     * Constructor.
     * Sets up a zoo containing different species of animal.
     */
    public OneSmallPartOfCodeBase() {
        List<Animal> animals = Arrays.asList(
        new Animal[]{
                new Walrus(),
                new Bear(),
                new Roadrunner()
        });
        zoo = new Zoo(animals);
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
      OneSmallPartOfCodeBase codeBase = new OneSmallPartOfCodeBase();
String blah = codeBase.toString();
System.out.println(blah.indexOf("Bear says: Smarter than the average beer.") == -1);
    }
}
