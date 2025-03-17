package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class Book {
    // -title: String
    private String title;
    private List<Author> authors; // 1..n authors

    // +Book(String title, String authorName): ctor
    public Book(String title, String authorName) throws EmptyAuthorListException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new EmptyAuthorListException("Author name cannot be empty.");
        }
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
    }

    // +Book(String title, List<Author> authors): ctor
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        this.title = title;
        // Store a copy or directly assign the incoming list.
        this.authors = new ArrayList<>(authors);
    }

    // +getAuthors(): List<Author>
    public List<Author> getAuthors() {
        return authors;
    }

    // +setAuthors(List<Author> authors): void
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        this.authors = new ArrayList<>(authors);
    }

    // +addAuthor(Author author): void
    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    // +getTitle(): String
    public String getTitle() {
        return title;
    }

    // +setTitle(String title): void
    public void setTitle(String title) {
        this.title = title;
    }
}
