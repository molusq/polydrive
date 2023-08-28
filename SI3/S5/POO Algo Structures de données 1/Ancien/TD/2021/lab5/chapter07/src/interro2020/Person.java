package interro2020;

public class Person {
    private final String name;
    final private int age;

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name + " " + age;
    }
}
