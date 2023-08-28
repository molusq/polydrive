package quality;

import java.util.*;

/**
 * Utility class for converting unordered people to ordered people.
 * Only has static methods.
 * @author Peter Sander
 */
final class People {

    /**
     * Can't instantiate since all methods are static.
     */
    private People() {
    }

    /**
     * Orders unordered people.
     * @param unorderedPeople
     * @return Ordered people.
     */
    static final TreeSet<Person> alwaysOrdered(List<Person> unorderedPeople) {
        unorderedPeople.sort(Person::compareTo);
        return new TreeSet<>(unorderedPeople);
    }
}