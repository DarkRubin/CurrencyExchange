package exceptions.Service;

import DTO.ExceptionDTO;

public class ExchangeRateNotFoundExceptionDTO extends ExceptionDTO {

    public ExchangeRateNotFoundExceptionDTO() {
        super.MESSAGE = "Валюта пара не найдена";
        super.HTTP_CODE = 404;
    }
}
