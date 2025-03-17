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
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }
        if (!users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
        Lending lending = new Lending(book, user);
        lendings.add(lending);
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (facultyMember == null || book == null) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist.");
        }
        if (!users.contains(facultyMember) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist in the system.");
        }
        for (Lending lending : lendings) {
            if (lending.getUser().equals(facultyMember) && lending.getBook().equals(book)) {
                lending.setDueDate(newDueDate);
                return;
            }
        }
        throw new UserOrBookDoesNotExistException("No lending found for this faculty member and book.");
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }
        if (!users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
        Lending toRemove = null;
        for (Lending lending : lendings) {
            if (lending.getUser().equals(user) && lending.getBook().equals(book)) {
                toRemove = lending;
                break;
            }
        }
        if (toRemove != null) {
            lendings.remove(toRemove);
        } else {
            throw new UserOrBookDoesNotExistException("No lending found for this user and book.");
        }
    }
}