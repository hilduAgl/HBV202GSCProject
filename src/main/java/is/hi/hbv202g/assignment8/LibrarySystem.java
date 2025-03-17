package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<Book> books;
    private List<User> users;
    private List<Lending> lendings;

    public LibrarySystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        lendings = new ArrayList<>();
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) throws EmptyAuthorListException {
        Book book = new Book(title, authorName);
        books.add(book);
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        Book book = new Book(title, authors);
        books.add(book);
    }

    public void addStudentUser(String name, boolean feePaid) {
        Student student = new Student(name, feePaid);
        users.add(student);
    }

    public void addFacultyMemberUser(String name, String department) {
        FacultyMember facultyMember = new FacultyMember(name, department);
        users.add(facultyMember);
    }

    public Book findBookByTitle(String title) {
        // Assignment says to return null
        return null;
    }

    public User findUserByName(String name) {
        // Assignment says to return null
        return null;
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        // Assignment says to leave this method empty
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        // Assignment says to leave this method empty
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        // Assignment says to leave this method empty
    }
}