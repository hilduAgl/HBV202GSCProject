package is.hi.hbv202g.assignment8;

/**
 * Exception class for when the author list is empty
 */
public class EmptyAuthorListException extends Exception{
    /**
     * Constructor
     * 
     * @param message error message
     */
    public EmptyAuthorListException(String message) {
        super(message);
    }
}