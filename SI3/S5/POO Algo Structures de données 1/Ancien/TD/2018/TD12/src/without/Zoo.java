package without;

import java.util.Arrays;
import java.util.List;


/**
 * Zoo containing animals.
 * @author (not) Peter Sander
 */
class Zoo {
    private List<Animal> animal;

    /**
     * Constructor.
     * @param animals Animals housed in the zoo.
     */
    Zoo(List<Animal> animal) {
        this.animal = animal;
    }

    /**
     * @return Information about all the animals in the zoo.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        animal.forEach(s -> sb.append(makeItTalk(s) + "\n"));
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
