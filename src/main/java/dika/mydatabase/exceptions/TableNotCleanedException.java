package dika.mydatabase.exceptions;

public class TableNotCleanedException extends Exception {
    public TableNotCleanedException(String message) {
        super(message);
    }

    public TableNotCleanedException(String message, Throwable cause) {
        super(message, cause);
    }
}
