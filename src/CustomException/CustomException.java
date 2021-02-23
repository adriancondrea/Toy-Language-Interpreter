package CustomException;

public class CustomException extends RuntimeException{
    public CustomException(){}
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}