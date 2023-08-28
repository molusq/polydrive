package ptech.groland;

/**
 * @author Florian Latapie
 */
public abstract class Person {
    private final String name;
    private final int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nID: " + id;
    }

    void print(){
        System.out.println(this.toString());
    }
}
