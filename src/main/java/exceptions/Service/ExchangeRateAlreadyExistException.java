package exceptions.Service;

import DTO.ExchangeRateDTO;

public class ExchangeRateAlreadyExistException extends ServiceException {

    public final ExchangeRateDTO savedExchangeRate;

    public ExchangeRateAlreadyExistException(ExchangeRateDTO exchangeRateDTO) {
        savedExchangeRate = exchangeRateDTO;
        MESSAGE = "Валютная пара с таким кодом уже существует";
        HTTP_CODE = 409;
    }
}
