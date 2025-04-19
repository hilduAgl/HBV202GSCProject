package is.hi.hbv202g.assignment8;

import java.util.List;
import java.util.ArrayList;

public class Book {
    private String title; 
    private List<Author> authors; 

  
    public Book(String title, String authorName) throws EmptyAuthorListException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new EmptyAuthorListException("Author name cannot be empty."); 
        }
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
    }


    public Book(String title, List<Author> authors) throws EmptyAuthorListException { 
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty."); 
        }
        this.title = title;
        this.authors = new ArrayList<>(authors);
    }


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

  
    public void addAuthor(Author author) { 
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public void borrow(User user) throws UserOrBookDoesNotExistException {
        // This could be a simple implementation that does nothing, or you can log borrowing info.
        System.out.println("Borrowing book: " + title);
    }
}