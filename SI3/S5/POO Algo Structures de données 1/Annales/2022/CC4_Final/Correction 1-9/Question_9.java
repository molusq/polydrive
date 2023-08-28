public class Library extends ItemDictionnary<Book> {

    private List<Book> collectedBooks = new ArrayList<>();

    /*
    Implémenter la méthode
    qui renvoie les livres qui ont été ajouté par addItem dans la bibliothèque, dans l'ordre d'ajout dans la bibliothèque (le plus ancien en premier) et qui sont toujours présents.
     */
    public List<Book> listAddedBooks(){
        List<Book> addedBooks = new ArrayList<>();
        if (collectedBooks.isEmpty())
            return addedBooks;
        for (Book b : collectedBooks){
            if (isMember(b)){
                addedBooks.add(b);
            }
        }
        return addedBooks;
    }


    private boolean isMember(Book employee) {
        if (employee==null)
            return false;
        List<Book> employeeList = this.get(employee.getId());
        if (employeeList == null)
            return false;
        for (Book e : employeeList){
            if (e == employee){
                return true;
            }
        }
        return false;
    }

    /**
     * Cette méthode recherche tous les livres qui ont le même ISBN que le livre passé en argument.
     * Si aucun livre n'est trouvé, la méthode renvoie un objet Optional contenant une copie du livre passé en argument.
     * Sinon, la méthode itère sur la liste de livres trouvés et essaie de les fusionner avec le livre passé en argument.
     * Si la fusion est réussie, le livre fusionné remplace le livre passé en argument et le processus continue.
     * Enfin, la méthode renvoie un objet Optional contenant le livre résultant de toutes les fusions.
     *
     * @param book
     * @return
     */
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

    @Override
    public void addItem(Book element) {
        collectedBooks.add(element);
        super.addItem(element);
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
