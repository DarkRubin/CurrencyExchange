package servlets;

import exceptions.Service.CodeInvalidException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
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
        String code = request.getPathInfo().replace("/", "");
        try {
            codeIsValid(code);
            Currency currency = service.findInTable(code);
            util.printResponseInJSON(currency, response);
        } catch (CurrencyNotFoundException | DbDontWorkException | CodeInvalidException e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }

    private void codeIsValid(String code) throws CodeInvalidException {
        if (code == null || code.length() != 3) {
            throw new CodeInvalidException();
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
