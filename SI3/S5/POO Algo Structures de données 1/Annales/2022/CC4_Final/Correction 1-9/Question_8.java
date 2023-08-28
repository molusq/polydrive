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


    public List<Book> sortByTitle() {
        List<Book> books = collectBooks();
        //n'utilise pas une lambda volontairement
        books.sort(new Comparator<>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });
        return books;
    }

    public List<Book> sortByAuthor() {
        List<Book> books = collectBooks();
        books.sort(new Comparator<>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getAuthor().compareTo(b2.getAuthor());
            }
        });
        return books;
    }

    private List<Book> collectBooks() {
        List<Book> list = new ArrayList<>();
        for (Collection<Book> value : values()){
            list.addAll(value);
        }
        return list;
    }

}

