package exceptions.Service;

import DTO.ExceptionDTO;

public class CurrencyAlreadyExistExceptionDTO extends ExceptionDTO {

    public CurrencyAlreadyExistExceptionDTO() {
        MESSAGE = "Валюта с таким кодом уже существует";
        HTTP_CODE = 409;
    }
}
