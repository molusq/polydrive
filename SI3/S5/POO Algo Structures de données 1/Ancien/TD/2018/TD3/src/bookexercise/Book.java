package bookexercise;
/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author (Insert your name here.)
 */
class Book {
    // The fields.
    private String author;
    private String title;

    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    private Book(String bookAuthor, String bookTitle) {
        author = bookAuthor;
        title = bookTitle;
    }

    // Add the methods here ...
}
