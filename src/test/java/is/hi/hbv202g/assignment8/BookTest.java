package is.hi.hbv202g.assignment8;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        try {
            book = new Book("Clean Code", "Robert C. Martin");
        } catch (EmptyAuthorListException e) {
            fail("Should not throw EmptyAuthorListException: " + e.getMessage());
        }
    }

    @Test
    public void testGetTitle() {
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    public void testAddAuthor() {
        Author newAuthor = new Author("Another Author");
        book.addAuthor(newAuthor);

        // Verify the new author was added
        assertEquals(2, book.getAuthors().size());
        assertEquals("Another Author", book.getAuthors().get(1).getName());
    }

    @Test(expected = EmptyAuthorListException.class)
    public void testAddBookWithEmptyAuthorList() throws EmptyAuthorListException {
        // This should throw an EmptyAuthorListException because the author is empty
        new Book("Invalid Book", "");
    }
}

