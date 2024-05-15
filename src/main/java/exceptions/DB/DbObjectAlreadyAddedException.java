package exceptions.DB;


import DTO.ExceptionDTO;

public class DbObjectAlreadyAddedException extends ExceptionDTO {

    public DbObjectAlreadyAddedException() {
        super.MESSAGE = "Объект уже существует в базе";
        super.HTTP_CODE = 500;
    }
}
