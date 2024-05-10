package exceptions.Service;

import model.Currency;

public class CurrencyAlreadyExistException extends ServiceException{

    public final Currency savedCurrency;

    public CurrencyAlreadyExistException(Currency savedCurrency) {
        this.savedCurrency = savedCurrency;
        MESSAGE = "Валюта с таким кодом уже существует";
        HTTP_CODE = 409;
    }
}
