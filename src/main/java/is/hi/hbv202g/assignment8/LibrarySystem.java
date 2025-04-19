package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Core business logic for the library.
 * <p>
 *  • maintains book &amp; user repositories<br>
 *  • handles lending transactions<br>
 *  • notifies {@link LibraryObserver}s via the Observer pattern
 */
public class LibrarySystem {

    private final List<Book> books;
    private final List<User> users;
    private final List<Lending> lendings;
    private final List<LibraryObserver> observers;

    public LibrarySystem() {
        books     = new ArrayList<>();
        users     = new ArrayList<>();
        lendings  = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /* -------------------------------------------------- */
    /* Observer maintenance                                */
    /* -------------------------------------------------- */
    public void addObserver(LibraryObserver o)    { observers.add(o);    }
    public void removeObserver(LibraryObserver o) { observers.remove(o); }

    private void notifyBookBorrowed(Book b) { observers.forEach(o -> o.onBookBorrowed(b)); }
    private void notifyBookReturned(Book b) { observers.forEach(o -> o.onBookReturned(b)); }
    private void notifyNewBookAdded(Book b) { observers.forEach(o -> o.onNewBookAdded(b)); }

    /* -------------------------------------------------- */
    /* Repository helpers                                 */
    /* -------------------------------------------------- */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName)
            throws EmptyAuthorListException {

        if (findBookByTitle(title) != null) {
            System.out.println("This book already exists in the library.");
            return;
        }
        Book book = new Book(title, authorName);
        books.add(book);
        notifyNewBookAdded(book);
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors)
            throws EmptyAuthorListException {

        if (findBookByTitle(title) != null) {
            System.out.println("This book already exists in the library.");
            return;
        }
        Book book = new Book(title, authors);
        books.add(book);
        notifyNewBookAdded(book);
    }

    public void addStudentUser(String name, boolean feePaid) {
        if (findUserByName(name) == null) users.add(new Student(name, feePaid));
    }

    public void addFacultyMemberUser(String name, String department) {
        if (findUserByName(name) == null) users.add(new FacultyMember(name, department));
    }

    public Book findBookByTitle(String title) {
        return books.stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
    }

    public User findUserByName(String name) {
        return users.stream()
                    .filter(u -> u.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);
    }

    /* -------------------------------------------------- */
    /* Lending operations                                 */
    /* -------------------------------------------------- */

    /**
     * Borrow a catalogued book with a registered user.
     */
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {

        if (user == null || book == null || !users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
        if (lendings.stream().anyMatch(l -> l.getBook().equals(book))) {
            throw new UserOrBookDoesNotExistException("This book is already borrowed.");
        }

        lendings.add(new Lending(book, user));
        notifyBookBorrowed(book);
    }

    /** Convenience wrapper (resolves names then delegates). */
    public void borrowBook(String userName, String bookTitle) {
        User user = findUserByName(userName);
        Book book = findBookByTitle(bookTitle);
        try {
            borrowBook(user, book);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Return a book previously borrowed.
     */
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {

        if (user == null || book == null || !users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }

        Lending lending = lendings.stream()
                                  .filter(l -> l.getBook().equals(book) && l.getUser().equals(user))
                                  .findFirst()
                                  .orElse(null);

        if (lending == null) {
            throw new UserOrBookDoesNotExistException("The book was not borrowed by this user.");
        }

        lendings.remove(lending);
        notifyBookReturned(book);
    }

    public void returnBook(String userName, String bookTitle) {
        User user = findUserByName(userName);
        Book book = findBookByTitle(bookTitle);
        try {
            returnBook(user, book);
            System.out.println(user.getName() + " returned " + book.getTitle());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
            throws UserOrBookDoesNotExistException {

        if (facultyMember == null || book == null || !users.contains(facultyMember) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist in the system.");
        }

        Lending lending = lendings.stream()
                                  .filter(l -> l.getBook().equals(book) && l.getUser().equals(facultyMember))
                                  .findFirst()
                                  .orElse(null);

        if (lending == null) {
            throw new UserOrBookDoesNotExistException("This lending does not exist.");
        }
        lending.setDueDate(newDueDate);
    }

    /* -------------------------------------------------- */
    /* Diagnostic helpers                                 */
    /* -------------------------------------------------- */
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("Books in the library:");
        books.forEach(b -> System.out.println("- " + b.getTitle() + " by " +
                b.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", "))));
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        System.out.println("Users in the system:");
        users.forEach(u -> System.out.println("- " + u.getName()));
    }

    /* -------------------------------------------------- */
    /* Getters (used by unit tests)                       */
    /* -------------------------------------------------- */
    public List<Book> getBooks() { return books; }
    public List<User> getUsers() { return users; }
}
