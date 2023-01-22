package homework.exceptions;

public class NoFunctionsFoundException extends RuntimeException{
    public NoFunctionsFoundException(){
        super("No functions found!");
    }
}
