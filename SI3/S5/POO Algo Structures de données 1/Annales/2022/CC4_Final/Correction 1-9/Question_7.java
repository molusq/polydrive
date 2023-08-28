package testFinal.Q7;

public class Library extends ItemDictionnary<Book> {
    
    public Optional<Book> mergeBooks(Book book) {
        if (book == null) {
            return Optional.empty();
        }
        List<Book> booksToMerge = findItems(book);
        if (booksToMerge == null || booksToMerge.isEmpty()) {
            return Optional.of(book.copy());
        }

        Book booktoMerge = book;
        for (Book b : booksToMerge) {
            Optional<Book> mergedBook = booktoMerge.merge(b);
            if (mergedBook.isPresent()) {
                booktoMerge = mergedBook.get();
            }
        }
        return Optional.of(booktoMerge);

    }
}
