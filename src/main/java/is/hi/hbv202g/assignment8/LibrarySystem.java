package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {

    // Fields to store books, users, and lendings
    private List<Book> books;
    private List<User> users;
    private List<Lending> lendings;

    // Constructor
    public LibrarySystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        lendings = new ArrayList<>();
    }

    // +addBookWithTitleAndNameOfSingleAuthor(String title, String authorName): void
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        try {
            Book book = new Book(title, authorName);
            books.add(book);
        } catch (EmptyAuthorListException e) {
            // Handle the exception as needed
            e.printStackTrace();
        }
    }

    // +addBookWithTitleAndAuthorList(String title, List<Author> authors): void
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) {
        try {
            Book book = new Book(title, authors);
            books.add(book);
        } catch (EmptyAuthorListException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    // +addStudentUser(String name, boolean feePaid): void
    public void addStudentUser(String name, boolean feePaid) {
        Student student = new Student(name, feePaid);
        users.add(student);
    }

    // +addFacultyMemberUser(String name, String department): void
    public void addFacultyMemberUser(String name, String department) {
        FacultyMember facultyMember = new FacultyMember(name, department);
        users.add(facultyMember);
    }

    // +findBookByTitle(String title): Book
    public Book findBookByTitle(String title) {
        // For now, just returning null (or implement a search if you want)
        return null;
    }

    // +findUserByName(String name): User
    public User findUserByName(String name) {
        // For now, just returning null
        return null;
    }

    // +borrowBook(User user, Book book): void
    public void borrowBook(User user, Book book) {
        // Method stub
    }

    // +extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate): void
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) {
        // Method stub
    }

    // +returnBook(User user, Book book): void
    public void returnBook(User user, Book book) {
        // Method stub
    }
}
