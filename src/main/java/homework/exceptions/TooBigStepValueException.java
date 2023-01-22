package homework.exceptions;

public class TooBigStepValueException extends IllegalArgumentException{
    public TooBigStepValueException() {
        super("too big step value!");
    }
}
