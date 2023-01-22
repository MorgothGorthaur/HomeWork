package homework.exceptions;

public class BadIdValueException extends ArrayIndexOutOfBoundsException{
    public BadIdValueException() {
        super("to big id!");
    }
}
