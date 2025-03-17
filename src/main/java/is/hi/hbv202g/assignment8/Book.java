package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors; // 1..n authors

    // ctor with a single author name
    public Book(String title, String authorName) throws EmptyAuthorListException {
        if (authorName == null || authorName.isEmpty()) {
            throw new EmptyAuthorListException("Author name cannot be empty");
        }
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
    }

    // ctor with a list of authors
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }
        this.title = title;
        // you could copy or store directly
        this.authors = new ArrayList<>(authors);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }
        this.authors = new ArrayList<>(authors);
    }

    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
