package exceptions.Service;

public abstract class ServiceException extends RuntimeException {

    protected String MESSAGE;
    protected int HTTP_CODE;

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public int getHttpCode() {
        return HTTP_CODE;
    }
}
