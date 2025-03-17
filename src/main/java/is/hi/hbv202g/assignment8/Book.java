package is.hi.hbv202g.assignment8;

import java.util.List;
import java.util.ArrayList;

public class Book {
    private String title;
    private List<Author> authors;

    // Constructor with single author
    public Book(String title, String authorName) throws EmptyAuthorListException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new EmptyAuthorListException("Author name cannot be empty.");
        }
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
    }

    // Constructor with list of authors
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        this.title = title;
        this.authors = new ArrayList<>(authors);
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        this.authors = new ArrayList<>(authors);
    }
}