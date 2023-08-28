package groland.admin.sploit;

/**
 * Exploits a weakness in Polytech'Groland's code.
 * @author Wilma
 * @author Florian Latapie
 */
public class Sploit {
    public void haxMyMarks(int[] myMarks) {
        for (int i = 0 ; i < myMarks.length ; i++){
            myMarks[i] = 20;
        }
    }
}