package exceptions.Service;


public class ExchangeRateAlreadyExistException extends ServiceException {

    public ExchangeRateAlreadyExistException() {
        MESSAGE = "Валютная пара с таким кодом уже существует";
        HTTP_CODE = 409;
    }
}
