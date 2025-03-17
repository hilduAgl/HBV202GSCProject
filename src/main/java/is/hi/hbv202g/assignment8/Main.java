package is.hi.hbv202g.assignment8;

public class Main {
    public static void main(String[] args) {
        LibrarySystem myLibrarySystem = new LibrarySystem();

        myLibrarySystem.addStudentUser("Alice", true);

        try {
            myLibrarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");
        } catch (EmptyAuthorListException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }
}