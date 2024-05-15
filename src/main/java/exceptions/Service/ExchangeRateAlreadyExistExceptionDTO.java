package exceptions.Service;


import DTO.ExceptionDTO;

public class ExchangeRateAlreadyExistExceptionDTO extends ExceptionDTO {

    public ExchangeRateAlreadyExistExceptionDTO() {
        MESSAGE = "Валютная пара с таким кодом уже существует";
        HTTP_CODE = 409;
    }
}
