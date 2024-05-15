package exceptions.Service;

import DTO.ExceptionDTO;

public class CurrencyNotFoundExceptionDTO extends ExceptionDTO {

    public CurrencyNotFoundExceptionDTO() {
        super.MESSAGE = "Валюта не найдена";
        super.HTTP_CODE = 404;
    }

}
