package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return null; 
    }

    public User findUserByName(String name) {
        return null; 
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }
        if (!users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (facultyMember == null || book == null) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist.");
        }
        if (!users.contains(facultyMember) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Faculty member or book does not exist in the system.");
        }
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }
        if (!users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist in the system.");
        }
    }

    public List<Book> getBooks() {
        return books;
    }
    public List<User> getUsers() {
        return users;
    }

    // Method to list all books
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

    // Method to list all users
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

    // Method to borrow a book
    public void borrowBook(String userName, String bookTitle) {
        User user = findUserByName(userName);
        Book book = findBookByTitle(bookTitle);
        try {
            borrowBook(user, book);
            lendings.add(new Lending(book, user));
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to return a book
    public void returnBook(String userName, String bookTitle) {
        User user = findUserByName(userName);
        Book book = findBookByTitle(bookTitle);
        try {
            returnBook(user, book);
            System.out.println(user.getName() + " returned " + book.getTitle());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}