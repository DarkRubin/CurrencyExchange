package exceptions.DB;

import DTO.ExceptionDTO;

public class DbObjectNotFoundException extends ExceptionDTO {
    public DbObjectNotFoundException() {
        super.MESSAGE = "Объект не найден в базе данных";
        super.HTTP_CODE = 500;
    }
}
