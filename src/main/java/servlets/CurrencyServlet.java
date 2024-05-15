package servlets;

import exceptions.Service.CodeInvalidExceptionDTO;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Currency;
import service.CurrenciesService;

import java.io.IOException;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends StartServlet {

    private final CurrenciesService service = new CurrenciesService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String code = util.getCodeFromPatch(request);
            Currency currency = service.find(code);
            util.printResponseInJSON(currency, response);
        } catch (CurrencyNotFoundExceptionDTO | DbDontWorkExceptionDTO | CodeInvalidExceptionDTO e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
