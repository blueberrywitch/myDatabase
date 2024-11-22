package dika.mydatabase.exceptions;

public class UserNotRemovedException extends Exception {
    public UserNotRemovedException(String message) {
        super(message);
    }

    public UserNotRemovedException(String message, Throwable cause) {
        super(message, cause);
    }
}
