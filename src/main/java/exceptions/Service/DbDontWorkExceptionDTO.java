package exceptions.Service;

import DTO.ExceptionDTO;

public class DbDontWorkExceptionDTO extends ExceptionDTO {

    public DbDontWorkExceptionDTO() {
        super.MESSAGE = "База данных не работает";
        super.HTTP_CODE = 500;
    }
}
