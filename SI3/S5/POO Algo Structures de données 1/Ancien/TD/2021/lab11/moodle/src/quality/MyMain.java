package quality;

import java.util.*;

import static quality.Person.Quality.DOUCHE;
import static quality.Person.Quality.HOSER;
import static quality.Person.Quality.WANNABE;
import static quality.Person.Quality.BADASS;

/**
 * @author Florian Latapie
 */
public class MyMain {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<Person>() {{
            add(new Person(DOUCHE));
            add(new Person(HOSER));
            add(new Person(WANNABE));
            add(new Person(BADASS));
        }};
        Collections.shuffle(people);
        Person otherPerson = null;
        TreeSet<Person> orderedPeople = People.alwaysOrdered(people);
        Iterator<Person> it = orderedPeople.iterator();
        Person thisPerson = it.next();
        while (it.hasNext()) {
            otherPerson = it.next();
            System.out.println(thisPerson.compareTo(otherPerson) < 0);
            thisPerson = otherPerson;
        }
    }
}
