package interro2020;

import java.util.ArrayList;

public class PeopleDemo {
    public static void main(String[] args){
        final ArrayList<Person> adults = new ArrayList() {{
            add(new Person("Larry", 42));
            add(new Person("Curly Joe", 22));
            add(new Person("Moe", 32));

        }};
        final People peeps = new People(adults);
        System.out.println(peeps);

        new Nasty(adults).minorize("Curly Joe", 12);
        System.out.println(peeps);

    }
}
