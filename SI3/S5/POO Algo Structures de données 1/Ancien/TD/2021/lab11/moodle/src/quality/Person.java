package quality;

import java.util.Objects;

/**
 * Models a person. Persons are ordered by their quality characteristic.
 *
 * @author Peter Sander
 * @author Florian Latapie
 */

public class Person implements Comparable<Person> {
    enum Quality {
        DOUCHE, HOSER, POSEUR, WANNABE, REAL_DEAL, BADASS
    }

    private Quality quality;

    Person(Quality quality) {
        this.quality = quality;
    }

    /**
     * Orders persons by their quality.
     *
     * @return n < 0 if this < other; n = 0 if this = other; n > 0 if this > other
     */
    @Override
    public int compareTo(Person other) {
        return this.quality.compareTo(other.quality);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return quality == person.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quality);
    }
}