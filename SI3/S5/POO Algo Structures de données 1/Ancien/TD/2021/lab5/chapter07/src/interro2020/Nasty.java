package interro2020;

import java.util.List;

public class Nasty {
    private final List<Person> peeps;

    Nasty(List<Person> peeps){
        this.peeps = peeps;
    }

    void minorize(String name, int age){
        for (int i = 0; i < peeps.size(); i++){
            if (peeps.get(i).getName().equals(name)){
                peeps.remove(i);
                peeps.add(new Person (name, age));
            }
        }
    }
}
