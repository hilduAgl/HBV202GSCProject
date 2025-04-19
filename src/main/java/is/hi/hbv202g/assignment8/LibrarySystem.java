package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibrarySystem {
    private List<Book> books;
    private List<User> users;
    private List<Lending> lendings;
    private List<LibraryObserver> observers;

    public LibrarySystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        lendings = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Add an observer to be notified when books are borrowed, returned, or added
    public void addObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    // Remove an observer
    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    // Notify observers when a book is borrowed
    private void notifyBookBorrowed(Book book) {
        for (LibraryObserver observer : observers) {
            observer.onBookBorrowed(book);
        }
    }

    // Notify observers when a book is returned
    private void notifyBookReturned(Book book) {
        for (LibraryObserver observer : observers) {
            observer.onBookReturned(book);
        }
    }

    // Notify observers when a new book is added
    private void notifyNewBookAdded(Book book) {
        for (LibraryObserver observer : observers) {
            observer.onNewBookAdded(book);
        }
    }

    // Add a book with a single author
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) throws EmptyAuthorListException {
        // Prevent duplicate book titles
        for (Book existingBook : books) {
            if (existingBook.getTitle().equalsIgnoreCase(title)) {
                System.out.println("This book already exists in the library.");
                return;
            }
        }

        Book book = new Book(title, authorName);
        books.add(book);
        notifyNewBookAdded(book);  // Notify observers when a new book is added
    }

    // Add a book with a list of authors
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        Book book = new Book(title, authors);
        books.add(book);
        notifyNewBookAdded(book);  // Notify observers when a new book is added
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
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Return null if book is not found
    }

    // Find a user by name
    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null; // Return null if user is not found
    }

    // Borrow a book
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }

        // Prevent borrowing the same book twice
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book)) {
                throw new UserOrBookDoesNotExistException("This book is already borrowed.");
            }
        }

        lendings.add(new Lending(book, user));
        notifyBookBorrowed(book); // Notify observers when a book is borrowed
    }

    // Return a book
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }

        boolean found = false;
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book) && lending.getUser().equals(user)) {
                lendings.remove(lending);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new UserOrBookDoesNotExistException("The book was not borrowed by this user.");
        }

        notifyBookReturned(book); // Notify observers when a book is returned
    }

    // List all books in the system
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", ")));
            }
        }
    }

    // List all users in the system
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("Users in the system:");
            for (User user : users) {
                System.out.println("- " + user.getName());
            }
        }
    }

    // Borrow a book by title and user name
    public void borrowBook(String userName, String bookTitle) {
        User user = findUserByName(userName);
        Book book = findBookByTitle(bookTitle);

        try {
            borrowBook(user, book);
            lendings.add(new Lending(book, user)); // Add lending entry
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Return a book by title and user name
    public void returnBook(String userName, String bookTitle) {
        User user = findUserByName(userName);
        Book book = findBookByTitle(bookTitle);

        try {
            returnBook(user, book); // Return book logic
            System.out.println(user.getName() + " returned " + book.getTitle());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Extend the lending period for a faculty member
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (facultyMember == null || book == null) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist.");
        }
        if (!users.contains(facultyMember) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist in the system.");
        }

        // Find the lending entry and extend the due date
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book) && lending.getUser().equals(facultyMember)) {
                lending.extendDueDate(newDueDate.getDayOfMonth());
                System.out.println("Lending extended for " + book.getTitle());
                return;
            }
        }

        throw new UserOrBookDoesNotExistException("This lending does not exist.");
    }

    // Getter methods
    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }
}