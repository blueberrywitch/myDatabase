package dika.mydatabase.exceptions;

public class UserNotSavedException extends Exception {
    public UserNotSavedException(String message) {
        super(message);
    }

    public UserNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
