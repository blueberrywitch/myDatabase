package dika.mydatabase.exceptions;

public class UsersNotGotException extends Exception{
    public UsersNotGotException(String massege){
        super (massege);
    }
    public UsersNotGotException(String message, Throwable cause){
        super(message, cause);
    }
}
