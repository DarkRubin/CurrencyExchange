package exceptions.Service;

import DTO.ExceptionDTO;

public class CodeInvalidExceptionDTO extends ExceptionDTO {

    public CodeInvalidExceptionDTO() {
        super.MESSAGE = "Код валюты отсутствует в адресе";
        super.HTTP_CODE = 400;
    }
}
