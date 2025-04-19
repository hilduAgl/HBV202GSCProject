package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class CompositeBook extends Book {
    private List<Book> volumes;
    private LibrarySystem librarySystem; // Reference to the library system

    public CompositeBook(String title, LibrarySystem librarySystem) throws EmptyAuthorListException {
        super(title, "");
        this.librarySystem = librarySystem; // Assign the library system
        volumes = new ArrayList<>();
    }

    public void addVolume(Book book) {
        volumes.add(book);
    }

    @Override
    public String getTitle() {
        return super.getTitle() + " (Omnibus)";
    }

    public List<Book> getVolumes() {
        return volumes;
    }

    // Overriding behavior for borrowing a composite book
    @Override
    public void borrow(User user) throws UserOrBookDoesNotExistException {
        System.out.println("Borrowing omnibus: " + getTitle());
        for (Book book : volumes) {
            // Borrow each volume of the omnibus using the library system
            librarySystem.borrowBook(user, book);
        }
    }
}