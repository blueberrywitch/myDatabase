package dika.mydatabase.exceptions;

public class TableNotDropedException extends Exception {
    public TableNotDropedException(String message) {
        super(message);
    }

    public TableNotDropedException(String message, Throwable cause) {
        super(message, cause);
    }
}
