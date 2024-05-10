package exceptions.Service;

public class CodeInvalidException extends ServiceException {

    public CodeInvalidException() {
        super.MESSAGE = "Код валюты отсутствует в адресе";
        super.HTTP_CODE = 400;
    }
}
