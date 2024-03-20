package Exception;

public class LMSException extends Exception{

    private ExceptionName exceptionName;

    public LMSException(ExceptionName exceptionName,String message) {
        super(message);
    }
}
