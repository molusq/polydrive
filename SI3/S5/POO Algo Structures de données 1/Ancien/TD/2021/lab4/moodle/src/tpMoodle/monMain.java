package tpMoodle;

import java.util.Arrays;
import java.util.List;

/**
 * @author Florian Latapie
 */
public class monMain {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        Looping looping = new Looping();
        System.out.println("foor");
        looping.foor(list);
        System.out.println("foreach");
        looping.foreach(list);
        System.out.println("whyle");
        looping.whyle(list);
        System.out.println("iterator");
        looping.iterator(list);
        System.out.println("forEach");
        looping.forEach(list);

        System.out.println("find -------------------");
        Finding finding = new Finding();
        System.out.println("foor");
        finding.foor(list, "B");
        finding.foor(list, "b");
        System.out.println("foreach");
        finding.foreach(list, "D");
        System.out.println("whyle");
        List<String> list2 = Arrays.asList("A", "B", "c", "D", "e", "f", "G", "H", "1", "J", "K", "l");
        finding.whyle(list2, "f");
        System.out.println("iterator");
        finding.iterator(list, "B");
        System.out.println("forEach");
        finding.forEach(list, "A");
    }
}
