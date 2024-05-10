package exceptions.Service;

public class CurrencyNotFoundException extends ServiceException {

    public CurrencyNotFoundException() {
        super.MESSAGE = "Валюта не найдена";
        super.HTTP_CODE = 404;
    }

}
