package homework.exceptions;

public class NotDoubleException extends NumberFormatException{
    public NotDoubleException() {
        super("it`s not a double!");
    }
}
