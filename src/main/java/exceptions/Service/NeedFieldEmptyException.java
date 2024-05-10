package exceptions.Service;

public class NeedFieldEmptyException extends ServiceException {

    public NeedFieldEmptyException() {
        MESSAGE = "Отсутствует нужное поле формы";
        HTTP_CODE = 400;
    }
}
