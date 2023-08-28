package bookexercise;

/**
 * @author Florian Latapie
 */
public class Main {
    public static void main(String[] args) {

        final String ref = "yet another random reference";
        Book book = new Book("O. Mega", "The Last Book", 475, false);
        book.setRefNumber(ref);
        System.out.println(book.isCourseText());
    }
}
