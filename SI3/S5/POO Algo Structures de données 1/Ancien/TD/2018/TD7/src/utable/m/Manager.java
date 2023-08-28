package utable.m;

import java.util.Date;


/**
 * Creates and manages a person.
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 * @author <your name here>
 */
public class Manager {
    private Person person;

    public Manager() {
        this.person = new Person("Fred", new Date(1999, 0, 1));
    }

    public Person getPerson() {
        return person;
    }
}