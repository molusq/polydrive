package utable.imm;

/**
 * Creates and manages a person.
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 * @author <your name here>
 */
public class Manager {
    private Person person;

    public Manager() {
        this.person = new Person("Fred", 1999);
    }

    public Person getPerson() {
        return person;
    }
}