public class Book implements Identifiable, Matchable{
    private final String title;
    private final String author;
    private final int year;
    private final String isbn;

    private static final String UNKNOWN = "unknown";
        //Question 1

        public Book(String title, String author, int year) {
            this(title, author, year, UNKNOWN);
        }

        public Book(String title, String author, int year, String isbn) {
        this.title = title != null ? title : "undefined";
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
        return (this.isbn.equals(book.isbn) && (! this.isbn.equals(UNKNOWN)) ) ||
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
        return  (this.title.contains(query) ||
                this.author.contains(query) ||
                String.valueOf(this.year).contains(query) ||
                this.isbn.contains(query)) ;
    }
}
