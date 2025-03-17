package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystemTest {

    @Test
    public void testAddBookWithSingleAuthor() throws EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        library.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");
        Book foundBook = library.findBookByTitle("Clean Code");
        assertNotNull(foundBook);
        assertEquals("Clean Code", foundBook.getTitle());
        assertEquals("Robert C. Martin", foundBook.getAuthors().get(0).getName());
    }

    @Test
    public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Martin Fowler"));
        authors.add(new Author("Kent Beck"));
        library.addBookWithTitleAndAuthorList("Refactoring", authors);
        Book foundBook = library.findBookByTitle("Refactoring");
        assertNotNull(foundBook);
        assertEquals(2, foundBook.getAuthors().size());
    }

    @Test
    public void testAddStudentUser() {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        User foundUser = library.findUserByName("Alice");
        assertNotNull(foundUser);
        assertEquals("Alice", foundUser.getName());
        assertTrue(foundUser instanceof Student);
        assertTrue(((Student) foundUser).isFeePaid());
    }

    @Test
    public void testAddFacultyMemberUser() {
        LibrarySystem library = new LibrarySystem();
        library.addFacultyMemberUser("Bob", "Computer Science");
        User foundUser = library.findUserByName("Bob");
        assertNotNull(foundUser);
        assertEquals("Bob", foundUser.getName());
        assertTrue(foundUser instanceof FacultyMember);
        assertEquals("Computer Science", ((FacultyMember) foundUser).getDepartment());
    }

    @Test
    public void testBorrowAndReturnBook() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        library.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");

        User alice = library.findUserByName("Alice");
        Book cleanCode = library.findBookByTitle("Clean Code");

        library.borrowBook(alice, cleanCode);
        library.returnBook(alice, cleanCode);
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowNonExistentBook() throws UserOrBookDoesNotExistException, EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        Book nonExistentBook = new Book("Non-Existent Book", "Unknown Author");
        library.borrowBook(library.findUserByName("Alice"), nonExistentBook);
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testReturnNonExistentBook() throws UserOrBookDoesNotExistException, EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        Book nonExistentBook = new Book("Non-Existent Book", "Unknown Author");
        library.returnBook(library.findUserByName("Alice"), nonExistentBook);
    }

    @Test
    public void testExtendLending() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem library = new LibrarySystem();
        library.addFacultyMemberUser("Bob", "Computer Science");
        library.addBookWithTitleAndNameOfSingleAuthor("Advanced Java", "Some Author");

        User bob = library.findUserByName("Bob");
        Book advJava = library.findBookByTitle("Advanced Java");

        library.borrowBook(bob, advJava);
        LocalDate newDueDate = LocalDate.now().plusDays(90);
        library.extendLending((FacultyMember) bob, advJava, newDueDate);
    }
}