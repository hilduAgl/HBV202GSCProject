package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the CompositeBook (Composite design pattern).
 */
public class CompositeBookTest {

    @Test
    public void testBorrowCompositeBook() throws Exception {
        // Setup library system and user
        LibrarySystem library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        User alice = library.findUserByName("Alice");

        // Create two regular books and add them to the catalogue
        library.addBookWithTitleAndNameOfSingleAuthor("Volume 1", "Author A");
        library.addBookWithTitleAndNameOfSingleAuthor("Volume 2", "Author B");
        Book v1 = library.findBookByTitle("Volume 1");
        Book v2 = library.findBookByTitle("Volume 2");

        // Create composite (omnibus) and add the two volumes
        CompositeBook omnibus = new CompositeBook("Epic Series", library);
        omnibus.addVolume(v1);
        omnibus.addVolume(v2);

        // Title should have suffix
        assertEquals("Epic Series (Omnibus)", omnibus.getTitle());

        // Borrow the composite -> should internally borrow both volumes
        omnibus.borrow(alice);

        // Returning each volume individually should now succeed
        library.returnBook(alice, v1);
        library.returnBook(alice, v2);
    }
}
