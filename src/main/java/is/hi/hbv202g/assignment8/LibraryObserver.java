package is.hi.hbv202g.assignment8;

public interface LibraryObserver {
    void onBookBorrowed(Book book);
    void onBookReturned(Book book);
    void onNewBookAdded(Book book);
}

