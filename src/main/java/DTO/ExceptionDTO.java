package DTO;

public abstract class ExceptionDTO extends RuntimeException {

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
