package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

/**
 * CompositeBook represents an omnibus made up of several Book volumes.
 * It is an implementation of the Composite design pattern.
 */
public class CompositeBook extends Book {

    private final List<Book> volumes;
    private final LibrarySystem librarySystem;

    /**
     * Constructs an omnibus.  We pass a harmless default author
     * ("Various Authors") to the super‑constructor so the non‑empty
     * author constraint in {@link Book} is satisfied.
     */
    public CompositeBook(String title, LibrarySystem librarySystem) throws EmptyAuthorListException {
        super(title, "Various Authors");
        this.librarySystem = librarySystem;
        this.volumes = new ArrayList<>();
    }

    /** Adds a single volume to the omnibus. */
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

    /**
     * Borrowing the composite forwards the borrow operation to each contained
     * volume through the {@link LibrarySystem}.
     */
    @Override
    public void borrow(User user) throws UserOrBookDoesNotExistException {
        System.out.println("Borrowing omnibus: " + getTitle());
        for (Book book : volumes) {
            librarySystem.borrowBook(user, book);
        }
    }
}
