package bookexercise;
/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author Florian Latapie
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 */
class Book {
    // The fields.
    final private String author;
    private final String title;
    private int pages;
    private String ref;
    private int borrowed;
    private final boolean courseText;

    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    Book(String bookAuthor, String bookTitle, int bookPages, boolean bookIsCourseText) {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
        ref = "";
        borrowed = 0;
        courseText = bookIsCourseText;
    }

    String getAuthor() {
        return author;
    }
    String getTitle() {
        return title;
    }
    String getRef() {
        return ref;
    }
    int getPages() {
        return pages;
    }
    int getBorrowed(){
        return borrowed;
    }


    void printAuthor(){
        System.out.println(author);
    }
    void printTitle(){
        System.out.println(title);
    }

    @Override
    public String toString(){
        if (ref.length() > 0){
            return "Title: "+title+"\nAuthor: "+author+"\nPages: "+pages+"\nReference: "+ref+"\nThis book has been borrowed "+borrowed+" times.";
        }
        return "Title: "+title+"\nAuthor: "+author+"\nPages: "+pages+"\nThis book has been borrowed "+borrowed+" times.";
    }

    void printDetails(){
        System.out.println(this.toString());
    }

    public void setRefNumber(String ref) {
        if (ref.length() <3){
            System.out.println("Gotcha! RefNumber must be of length >= 3, but was "+ref.length());
        }else {
            this.ref = ref;
        }
    }

    public String getRefNumber() {
        return ref;
    }

    public void borrow() {
        borrowed ++;
    }

    public boolean isCourseText() {
        return courseText;
    }
}
