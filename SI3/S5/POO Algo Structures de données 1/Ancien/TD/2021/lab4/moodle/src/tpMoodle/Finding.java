package tpMoodle;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Florian Latapie
 */
public class Finding {
    void foor(List<String> list, String item) {
        if (list.contains(item)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(item))
                    System.out.println(i + 1 + ": " + list.get(i));
            }
        } else {
            System.out.println(item + " not found");
        }
    }

    void foreach(List<String> list, String item) {
        int i = 1;
        if (list.contains(item)) {
            for (String listItem : list) {
                if (listItem.equals(item))
                    System.out.println(i + ": " + item);
                i++;
            }
        } else {
            System.out.println(item + " not found");
        }
    }

    void whyle(List<String> list, String item) {
        int i = 0;
        if (list.contains(item)) {
            while (i < list.size()) {
                if (list.get(i).equals(item)) {
                    System.out.println(i + 1 + ": " + list.get(i));
                }
                i++;
            }
        } else {
            System.out.println(item + " not found");
        }
    }

    void iterator(List<String> list, String item) {
        int i = 1;
        if (list.contains(item)) {
            for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
                String val = it.next();
                if (val.equals(item)) {
                    System.out.println(i + ": " + val);
                }
                i++;
            }
        } else {
            System.out.println(item + " not found");
        }
    }

    void forEach(List<String> list, String item) {
        var ref = new Object() {
            int i = 1;
        };
        if (list.contains(item)) {
            list.forEach(action -> {
                if (action.equals(item)) {
                    System.out.println(ref.i + ": " + action);
                    ref.i++;
                }
            });
        } else {
            System.out.println(item + " not found");
        }
    }
}
