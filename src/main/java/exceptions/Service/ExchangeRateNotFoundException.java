package exceptions.Service;

public class ExchangeRateNotFoundException extends ServiceException {

    public ExchangeRateNotFoundException() {
        super.MESSAGE = "Валюта пара не найдена";
        super.HTTP_CODE = 404;
    }
}
