package is.hi.hbv202g.assignment8;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class LendingTest {
    private Lending lending;
    private Book book;
    private User user;

    @Before
    public void setUp() throws EmptyAuthorListException {
        // Create a Book and User object for testing
        book = new Book("Clean Code", "Robert C. Martin");
        user = new Student("Alice", true);

        // Create a Lending object
        lending = new Lending(book, user);
    }

    @Test
    public void testConstructor() {
        // Verify that the constructor initializes the fields correctly
        assertEquals(book, lending.getBook());
        assertEquals(user, lending.getUser());

        // Verify that the due date is set to 30 days from now
        LocalDate expectedDueDate = LocalDate.now().plusDays(30);
        assertEquals(expectedDueDate, lending.getDueDate());
    }

    @Test
    public void testSetDueDate() {
        // Set a new due date
        LocalDate newDueDate = LocalDate.now().plusDays(60);
        lending.setDueDate(newDueDate);

        // Verify that the due date is updated
        assertEquals(newDueDate, lending.getDueDate());
    }

    @Test
    public void testSetBook() throws EmptyAuthorListException {
        // Create a new Book object
        Book newBook = new Book("Refactoring", "Martin Fowler");

        // Set the new book
        lending.setBook(newBook);

        // Verify that the book is updated
        assertEquals(newBook, lending.getBook());
    }

    @Test
    public void testSetUser() {
        // Create a new User object
        User newUser = new FacultyMember("Bob", "Computer Science");

        // Set the new user
        lending.setUser(newUser);

        // Verify that the user is updated
        assertEquals(newUser, lending.getUser());
    }
}