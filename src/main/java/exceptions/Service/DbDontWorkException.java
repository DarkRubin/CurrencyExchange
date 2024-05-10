package exceptions.Service;

public class DbDontWorkException extends ServiceException {

    public DbDontWorkException() {
        super.MESSAGE = "База данных не работает";
        super.HTTP_CODE = 500;
    }
}
