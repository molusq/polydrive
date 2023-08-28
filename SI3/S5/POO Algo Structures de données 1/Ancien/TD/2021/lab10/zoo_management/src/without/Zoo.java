package without;

import java.util.Arrays;
import java.util.List;


/**
 * Zoo containing animals.
 * @author (not) Peter Sander
 * @author Florian Latapie
 */
class Zoo {
    private List<Animal> animals;

    /**
     * Constructor.
     * @param animals Animals housed in the zoo.
     */
    Zoo(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * @return Information about all the animals in the zoo.
     */
    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       animals.forEach(animal -> sb.append(makeItTalk(animal)+System.lineSeparator()));
       return sb.toString();
    }

    /**
     * @param species Makes this species of animal talk.
     * @return Favourite saying.
     */
    String makeItTalk(Animal species) {
        return species.talk();
    }
}
