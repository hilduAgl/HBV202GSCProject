package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

public class Lending {
    // -dueDate: java.time.LocalDate
    private LocalDate dueDate;
    private Book book;
    private User user;

    // +Lending(Book book, User user): ctor
    // The assignment says: "Constructor sets a due date 30 days from now."
    public Lending(Book book, User user) {
        this.book = book;
        this.user = user;
        this.dueDate = LocalDate.now().plusDays(30);
    }

    // +getDueDate(): LocalDate
    public LocalDate getDueDate() {
        return dueDate;
    }

    // +setDueDate(LocalDate dueDate): void
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // +getBook(): Book
    public Book getBook() {
        return book;
    }

    // +setBook(Book book): void
    public void setBook(Book book) {
        this.book = book;
    }

    // +getUser(): User
    public User getUser() {
        return user;
    }

    // +setUser(User user): void
    public void setUser(User user) {
        this.user = user;
    }
}
