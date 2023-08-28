package tpMoodle;

import java.util.Iterator;
import java.util.List;

/**
 * @author Florian Latapie
 */
public class Looping {
    void foor(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ": " + list.get(i));
        }
    }

    void foreach(List<String> list) {
        int i = 1;
        for (String item : list) {
            System.out.println(i + ": " + item);
            i++;
        }
    }

    void whyle(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            System.out.println(i + 1 + ": " + list.get(i));
            i++;
        }
    }

    void iterator(List<String> list) {
        int i = 1;
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            System.out.println(i + ": " + it.next());
            i++;
        }
    }

    void forEach(List<String> list) {
        var ref = new Object() {
            int i = 1;
        };
        list.forEach(action -> {
            System.out.println(ref.i + ": " + action);
            ref.i++;
        });
    }
}
