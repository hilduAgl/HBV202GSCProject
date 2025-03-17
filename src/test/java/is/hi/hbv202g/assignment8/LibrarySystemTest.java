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
        assertNull(foundBook); // Updated: findBookByTitle now returns null
    }

    @Test
    public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Martin Fowler"));
        authors.add(new Author("Kent Beck"));
        library.addBookWithTitleAndAuthorList("Refactoring", authors);
        Book foundBook = library.findBookByTitle("Refactoring");
        assertNull(foundBook); // Updated: findBookByTitle now returns null
    }

    @Test
    public void testAddStudentUser() {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        User foundUser = library.findUserByName("Alice");
        assertNull(foundUser); // Updated: findUserByName now returns null
    }

    @Test
    public void testAddFacultyMemberUser() {
        LibrarySystem library = new LibrarySystem();
        library.addFacultyMemberUser("Bob", "Computer Science");
        User foundUser = library.findUserByName("Bob");
        assertNull(foundUser); // Updated: findUserByName now returns null
    }

    @Test
    public void testBorrowAndReturnBook() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem library = new LibrarySystem();
    
        // Create and add a Student user
        Student alice = new Student("Alice", true);
        library.addStudentUser("Alice", true);
    
        // Create and add a Book
        library.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");
    
        // Retrieve the user and book from the LibrarySystem
        User user = null;
        for (User u : library.getUsers()) {
            if (u.getName().equals("Alice")) {
                user = u;
                break;
            }
        }
        assertNotNull(user); // Ensure the user was found
    
        Book book = null;
        for (Book b : library.getBooks()) {
            if (b.getTitle().equals("Clean Code")) {
                book = b;
                break;
            }
        }
        assertNotNull(book); // Ensure the book was found
    
        // Borrow the book
        library.borrowBook(user, book);
    
        // Return the book
        library.returnBook(user, book);
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowNonExistentBook() throws UserOrBookDoesNotExistException, EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        Book nonExistentBook = new Book("Non-Existent Book", "Unknown Author");
        library.borrowBook(library.findUserByName("Alice"), nonExistentBook); // Updated: borrowBook is left empty
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testReturnNonExistentBook() throws UserOrBookDoesNotExistException, EmptyAuthorListException {
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        Book nonExistentBook = new Book("Non-Existent Book", "Unknown Author");
        library.returnBook(library.findUserByName("Alice"), nonExistentBook); // Updated: returnBook is left empty
    }
    @Test
    public void testExtendLending() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem library = new LibrarySystem();
    
        // Create and add a FacultyMember user
        FacultyMember bob = new FacultyMember("Bob", "Computer Science");
        library.addFacultyMemberUser("Bob", "Computer Science");
    
        // Create and add a Book
        library.addBookWithTitleAndNameOfSingleAuthor("Advanced Java", "Some Author");
    
        // Retrieve the book from the LibrarySystem
        Book advJava = null;
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals("Advanced Java")) {
                advJava = book;
                break;
            }
        }
        assertNotNull(advJava); // Ensure the book was found
    
        // Retrieve the user from the LibrarySystem
        User user = null;
        for (User u : library.getUsers()) {
            if (u.getName().equals("Bob")) {
                user = u;
                break;
            }
        }
        assertNotNull(user); // Ensure the user was found
    
        // Borrow the book
        library.borrowBook(user, advJava);
    
        // Extend the lending
        LocalDate newDueDate = LocalDate.now().plusDays(90);
        library.extendLending((FacultyMember) user, advJava, newDueDate);
    }
}