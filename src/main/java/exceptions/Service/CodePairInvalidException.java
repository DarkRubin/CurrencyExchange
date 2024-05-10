package exceptions.Service;

public class CodePairInvalidException extends ServiceException {

    public CodePairInvalidException() {
        MESSAGE = "Коды валют пары отсутствуют в адресе";
        HTTP_CODE = 400;
    }
}
