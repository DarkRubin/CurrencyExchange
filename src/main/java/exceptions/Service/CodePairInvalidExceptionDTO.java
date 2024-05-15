package exceptions.Service;

import DTO.ExceptionDTO;

public class CodePairInvalidExceptionDTO extends ExceptionDTO {

    public CodePairInvalidExceptionDTO() {
        MESSAGE = "Коды валют пары отсутствуют в адресе";
        HTTP_CODE = 400;
    }
}
