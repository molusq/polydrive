public class Book  {
    // Déclaration des champs
    private final String title;
    private final String author;
    private final int year;



    //Question 1

    public Book(String title, String author, int year) {
        this.title = title != null ? title : "undefined";
        this.author = author != null ? author : "unknown";
        this.year = year;
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
}