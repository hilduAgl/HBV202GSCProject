package is.hi.hbv202g.assignment8;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main(String[] args) {
        LibrarySystem myLibrarySystem = new LibrarySystem();
    

        myLibrarySystem.addStudentUser("Alice", true);
        myLibrarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");

    }
}
