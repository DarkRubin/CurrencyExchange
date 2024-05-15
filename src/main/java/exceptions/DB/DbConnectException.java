package exceptions.DB;


import DTO.ExceptionDTO;

public class DbConnectException extends ExceptionDTO {

    public DbConnectException() {
        super.MESSAGE = "Нет подключения к базе данных";
        super.HTTP_CODE = 500;
    }
}
