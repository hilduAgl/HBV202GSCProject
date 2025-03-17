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

    // Add a book with a single author
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        try {
            Book book = new Book(title, authorName);
            books.add(book);
        } catch (EmptyAuthorListException e) {
            e.printStackTrace();
        }
    }

    // Add a book with a list of authors
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) {
        try {
            Book book = new Book(title, authors);
            books.add(book);
        } catch (EmptyAuthorListException e) {
            e.printStackTrace();
        }
    }

    // Add a student user
    public void addStudentUser(String name, boolean feePaid) {
        Student student = new Student(name, feePaid);
        users.add(student);
    }

    // Add a faculty member user
    public void addFacultyMemberUser(String name, String department) {
        FacultyMember facultyMember = new FacultyMember(name, department);
        users.add(facultyMember);
    }

    // Find a book by title
    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    // Find a user by name
    public User findUserByName(String name) {
        for (User u : users) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    // Borrow a book
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }
        if (!users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
        Lending newLending = new Lending(book, user);
        lendings.add(newLending);
    }

    // Extend lending (only for faculty members)
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (facultyMember == null || book == null) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist.");
        }
        if (!users.contains(facultyMember) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist in the system.");
        }
        for (Lending l : lendings) {
            if (l.getUser().equals(facultyMember) && l.getBook().equals(book)) {
                if (newDueDate.isAfter(l.getDueDate())) {
                    l.setDueDate(newDueDate);
                } else {
                    throw new IllegalArgumentException("New due date must be after the current due date.");
                }
                return;
            }
        }
        throw new UserOrBookDoesNotExistException("No lending found for this faculty member and book.");
    }

    // Return a book
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }
        if (!users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
        Lending toRemove = null;
        for (Lending l : lendings) {
            if (l.getUser().equals(user) && l.getBook().equals(book)) {
                toRemove = l;
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