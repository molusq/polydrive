package utable.imm;

import java.util.Date;

/**
 * Models aspects of a person.
 * This class is immutable, ie, once the constructor has finished
 * instantiating an object there is no way to modify it.
 * It can't even be subclassed.
 * And yes, I know that most of java.util.Date is deprecated, but life
 * is too short to do things properly here.
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 * @author <a href="mailto:flo@unice.fr">florian</a>
 */
@SuppressWarnings("deprecation")
public final class Person {
    
    private String name;
    private int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    /**
     * @return Name and current age in years (more-or-less).
     */
    @Override
    public String toString() {
        int age = 2017 - birthYear;
        return "My name is " + name + "\n" + "age = " + age;
    }
}