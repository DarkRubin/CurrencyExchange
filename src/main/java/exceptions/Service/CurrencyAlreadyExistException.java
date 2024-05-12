package exceptions.Service;

public class CurrencyAlreadyExistException extends ServiceException{

    public CurrencyAlreadyExistException() {
        MESSAGE = "Валюта с таким кодом уже существует";
        HTTP_CODE = 409;
    }
}
