package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystemTest {

    @Test
    public void testAddStudentUserAndFindUser() {
        LibrarySystem system = new LibrarySystem();
        
        // Add a student user
        system.addStudentUser("Alice", true);
        
        // Find the user
        User found = system.findUserByName("Alice");
        assertNotNull("Expected to find user named Alice", found);
        assertEquals("Names should match", "Alice", found.getName());
        // Check that it's actually a Student and fee is paid
        assertTrue("Found user should be a Student", found instanceof Student);
        Student aliceStudent = (Student) found;
        assertTrue("Alice's fee should be paid", aliceStudent.isFeePaid());
    }
    
    @Test
    public void testAddFacultyMemberAndFindUser() {
        LibrarySystem system = new LibrarySystem();
        
        // Add a faculty member user
        system.addFacultyMemberUser("Bob", "Computer Science");
        
        // Find the user
        User found = system.findUserByName("Bob");
        assertNotNull("Expected to find user named Bob", found);
        assertEquals("Bob", found.getName());
        
        // Check that it's actually a FacultyMember
        assertTrue("Found user should be a FacultyMember", found instanceof FacultyMember);
        FacultyMember bobFaculty = (FacultyMember) found;
        assertEquals("Computer Science", bobFaculty.getDepartment());
    }

    @Test
    public void testAddBookWithSingleAuthorAndFindBook() {
        LibrarySystem system = new LibrarySystem();
        
        // Add a book with single author
        system.addBookWithTitleAndNameOfSingleAuthor("Design Patterns", "Gamma");
        
        // Find the book
        Book foundBook = system.findBookByTitle("Design Patterns");
        assertNotNull("Expected to find book titled 'Design Patterns'", foundBook);
        assertEquals("Design Patterns", foundBook.getTitle());
        assertEquals("Gamma", foundBook.getAuthors().get(0).getName());
    }
    
    @Test
    public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException {
        LibrarySystem system = new LibrarySystem();
        
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Martin Fowler"));
        authors.add(new Author("Kent Beck"));
        
        // Add a book with multiple authors
        system.addBookWithTitleAndAuthorList("Refactoring", authors);

        // Check the book is found
        Book foundBook = system.findBookByTitle("Refactoring");
        assertNotNull(foundBook);
        assertEquals(2, foundBook.getAuthors().size());
    }
    
    @Test
    public void testBorrowAndReturnBook() {
        LibrarySystem system = new LibrarySystem();
        
        // Add some users and books
        system.addStudentUser("Alice", true);
        system.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");
        
        // Retrieve them
        User alice = system.findUserByName("Alice");
        Book cleanCode = system.findBookByTitle("Clean Code");
        
        // Borrow
        system.borrowBook(alice, cleanCode);
        
        // Optionally, you might want a method to check if a book is currently borrowed.
        // For demonstration, let's check if we can forcibly 'return' it.
        // Return
        system.returnBook(alice, cleanCode);
        
        // If you had a method like 'isBookLentOut()', you could assert it is false now.
        // For example, if you implemented it, you'd do:
        //   assertFalse(system.isBookLentOut(cleanCode));
    }

    // Example test to show how a faculty member can extend the due date
    // (This assumes your 'extendLending' method updates the dueDate properly
    //  only for the correct faculty member.)
    @Test
    public void testExtendLendingByFaculty() {
        LibrarySystem system = new LibrarySystem();
        
        // Add a faculty user and a book
        system.addFacultyMemberUser("Bob", "Computer Science");
        system.addBookWithTitleAndNameOfSingleAuthor("Advanced Java", "Some Author");
        
        // Retrieve them
        User bob = system.findUserByName("Bob");
        Book advJava = system.findBookByTitle("Advanced Java");
        
        // Borrow the book
        system.borrowBook(bob, advJava);
        
        // Extend the lending
        LocalDate newDueDate = LocalDate.now().plusDays(90);
        system.extendLending((FacultyMember) bob, advJava, newDueDate);
        

    }
}
