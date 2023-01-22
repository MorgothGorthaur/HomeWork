package homework.exceptions;

public class StartValueIsBiggerThenEndValueException extends IllegalArgumentException{
    public StartValueIsBiggerThenEndValueException() {
        super("start value must be lower then end value!");
    }
}
