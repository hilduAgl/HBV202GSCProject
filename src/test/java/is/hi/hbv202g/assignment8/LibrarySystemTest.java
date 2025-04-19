package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for {@link LibrarySystem}.
 */
public class LibrarySystemTest {

    /* ------------------------------------------------------------------
     *  Repository‑update tests
     * ------------------------------------------------------------------ */

    @Test
    public void testAddBookWithSingleAuthor() throws EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        library.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");

        Book found = library.findBookByTitle("Clean Code");
        assertNotNull(found);
        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();

        List<Author> authors = List.of(new Author("Martin Fowler"),
                                       new Author("Kent Beck"));
        library.addBookWithTitleAndAuthorList("Refactoring", authors);

        Book found = library.findBookByTitle("Refactoring");
        assertNotNull(found);
        assertEquals(2, found.getAuthors().size());
    }

    @Test
    public void testAddStudentUser() {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);

        User found = library.findUserByName("Alice");
        assertNotNull(found);
        assertTrue(found instanceof Student);
    }

    @Test
    public void testAddFacultyMemberUser() {
        LibrarySystem library = new LibrarySystem();
        library.addFacultyMemberUser("Bob", "Computer Science");

        User found = library.findUserByName("Bob");
        assertNotNull(found);
        assertTrue(found instanceof FacultyMember);
    }

    /* ------------------------------------------------------------------
     *  Lending workflow
     * ------------------------------------------------------------------ */

    @Test
    public void testBorrowAndReturnBook() throws Exception {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        library.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");

        User alice = library.findUserByName("Alice");
        Book cleanCode = library.findBookByTitle("Clean Code");

        library.borrowBook(alice, cleanCode);   // no exception
        library.returnBook(alice, cleanCode);   // no exception
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowNonExistentBook() throws Exception {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);

        User alice = library.findUserByName("Alice");
        Book ghost = new Book("Ghost Book", "Nobody");

        library.borrowBook(alice, ghost);   // should now throw
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testReturnNonExistentBook() throws Exception {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);

        User alice = library.findUserByName("Alice");
        Book ghost = new Book("Ghost Book", "Nobody");

        library.returnBook(alice, ghost);   // should throw
    }

    @Test
    public void testExtendLending() throws Exception {
        LibrarySystem library = new LibrarySystem();
        library.addFacultyMemberUser("Bob", "Computer Science");
        library.addBookWithTitleAndNameOfSingleAuthor("Advanced Java", "Some Author");

        FacultyMember bob = (FacultyMember) library.findUserByName("Bob");
        Book advJava = library.findBookByTitle("Advanced Java");

        library.borrowBook(bob, advJava);

        LocalDate newDue = LocalDate.now().plusDays(90);
        library.extendLending(bob, advJava, newDue);   // no exception
    }

    /* ------------------------------------------------------------------
     *  Observer pattern
     * ------------------------------------------------------------------ */

    @Test
    public void testObserverNotifications() throws Exception {
        LibrarySystem library = new LibrarySystem();
        List<Book> events = new ArrayList<>();

        LibraryObserver observer = new LibraryObserver() {
            @Override public void onBookBorrowed(Book b) { events.add(b); }
            @Override public void onBookReturned(Book b){ events.add(b); }
            @Override public void onNewBookAdded(Book b){ events.add(b); }
        };
        library.addObserver(observer);

        library.addBookWithTitleAndNameOfSingleAuthor("DDD", "Eric Evans");
        library.addStudentUser("Ann", true);

        User ann = library.findUserByName("Ann");
        Book ddd = library.findBookByTitle("DDD");

        library.borrowBook(ann, ddd);
        library.returnBook(ann, ddd);

        assertEquals(3, events.size());   // added, borrowed, returned
        assertTrue(events.contains(ddd));
    }
}
