package without;

/**
 * @author Florian Latapie
 */
public class MyMain {
    public static void main(String[] args) {
        OneSmallPartOfCodeBase codeBase = new OneSmallPartOfCodeBase();
        String blah = codeBase.toString();
        System.out.println(blah.indexOf("Bear says: Smarter than the average beer.") == -1);
        
    }
}
