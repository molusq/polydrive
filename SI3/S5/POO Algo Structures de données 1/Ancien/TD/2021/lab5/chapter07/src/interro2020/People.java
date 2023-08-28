package interro2020;

import java.util.ArrayList;
import java.util.List;

public class People {
    private final List<Person> peeps;

    People(ArrayList<Person> peeps){
        this.peeps = (ArrayList<Person>)peeps.clone();
    }

    @Override
    public String toString(){
        String str = "";
        for(Person p : peeps){
            str += p.toString()+"\n";
        }
        return str;
    }
}
