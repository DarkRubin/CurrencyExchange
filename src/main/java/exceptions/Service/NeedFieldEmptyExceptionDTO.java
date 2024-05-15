package exceptions.Service;

import DTO.ExceptionDTO;

public class NeedFieldEmptyExceptionDTO extends ExceptionDTO {

    public NeedFieldEmptyExceptionDTO() {
        MESSAGE = "Отсутствует нужное поле формы";
        HTTP_CODE = 400;
    }
}
