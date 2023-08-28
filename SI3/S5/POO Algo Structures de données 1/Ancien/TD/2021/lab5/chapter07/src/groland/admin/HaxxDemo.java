package groland.admin;

import groland.admin.sploit.Sploit;

import java.util.HashMap;

/**
 * Demos a weakness in Polytech'Groland's marks
 * administration code.
 * @author Peter Sander
 * @author Florian Latapie
 */
class HaxxDemo {
    public static void main(String[] args) {
        /*// this is voodoo, but it correctly intializes marks
        HashMap<String, int[]> marksMap = new HashMap<String, int[]>() {{
            put("Barney", new int[]{12, 8, 7});
            put("Fred", new int[]{7, 9});
            put("Wilma", new int[]{15, 13, 3});
        }};
        Marks marks = new Marks(marksMap);
        Consultor consulter = new Consultor(marks);
        System.out.println("Administration consults student marks");
        consulter.displayMarks();*/
        // this is voodoo, but it correctly intializes marks
        HashMap<String, int[]> marksMap = new HashMap<String, int[]>() {{
            put("Barney", new int[]{12, 8, 7});
            put("Fred", new int[]{7, 9});
            put("Wilma", new int[]{15, 13, 3});
        }};
        Marks marks = new Marks(marksMap);
        Consultor consulter = new Consultor(marks);
        System.out.println("Administration consults student marks");
        consulter.displayMarks();

        // Wilma introduces exploit
        new Sploit().haxMyMarks(marks.getMarks("Wilma"));
        System.out.println("Administration consults student marks again");
        consulter.displayMarks();
    }
}