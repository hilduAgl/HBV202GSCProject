package is.hi.hbv202g.assignment8;

public class LibraryUI implements LibraryObserver {
    @Override
    public void onBookBorrowed(Book book) {
        System.out.println("A book has been borrowed: " + book.getTitle());
    }

    @Override
    public void onBookReturned(Book book) {
        System.out.println("A book has been returned: " + book.getTitle());
    }

    @Override
    public void onNewBookAdded(Book book) {
        System.out.println("A new book has been added: " + book.getTitle());
    }
}
