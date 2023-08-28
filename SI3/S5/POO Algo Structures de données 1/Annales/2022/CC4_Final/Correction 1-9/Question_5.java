public class Book implements Identifiable, Matchable {
    private final String title;
    private final String author;
    private final int year;
    private final String isbn;

    private static final String UNKNOWN = "unknown";
    private static final String UNDEFINED = "undefined";
    //Question 1

    public Book(String title, String author, int year) {
        this(title, author, year, UNKNOWN);
    }

    public Book(String title, String author, int year, String isbn) {
        this.title = title != null ? title : UNDEFINED;
        this.author = author != null ? author : UNKNOWN;
        this.year = year;
        this.isbn = isbn != null ? isbn : UNKNOWN;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear() {
        return this.year;
    }


    @Override
    public String getId() {
        return this.isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return (this.isbn.equals(book.isbn) && (!this.isbn.equals(UNKNOWN))) ||
                (this.isbn.equals(UNKNOWN) && book.isbn.equals(UNKNOWN) &&
                        this.title.equals(book.title) &&
                        this.author.equals(book.author));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getId().hashCode();
        result = 31 * result + this.getTitle().hashCode();
        result = 31 * result + this.getAuthor().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", isbn='" + getId() + '\'' +
                '}';
    }

    public boolean match(String query) {
        return (this.title.contains(query) ||
                this.author.contains(query) ||
                String.valueOf(this.year).contains(query) ||
                this.isbn.contains(query));
    }


    public Book copy() {
        return new Book(this.title, author, year, isbn);
    }


    public Optional<Book> merge(Book b1) {
        if (areMergeable(this, b1)) {
            String newIsbn = this.mergeISBN(b1.isbn);
            String newTitle = this.mergeIntitule(this.title, b1.title, UNDEFINED);
            String newAuthor = this.mergeIntitule(this.author, b1.author, UNKNOWN);
            return Optional.of(new Book(newTitle, newAuthor, this.year, newIsbn));
        } else return Optional.empty();

    }

    private String mergeIntitule(String title1, String title2, String neutre) {
        if (title2.equals(neutre))
            return title1;
        if (title1.equals(neutre))
            return title2;
        if (title2.contains(title1))
            return title2;
        if (title1.contains((title2)))
            return title1;
        return title1 + " or " + title2;
    }

    private String mergeISBN(String isbn) {
        if (!this.isbn.equals(UNKNOWN))
            return this.isbn;
        return isbn;
    }

    private boolean areMergeable(Book b1, Book b2) {
        if ((b1 == null) || (b2 == null))
            return false;
        if ((b1.isbn.equals(b2.isbn)) || (b1.isbn.equals(UNKNOWN)) || (b2.isbn.equals(UNKNOWN)))
            return (b1.year == b2.year);
        else return false;
    }
}
