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
        try {
            String code = util.getCodeFromPatch(request);
            Currency currency = service.findInTable(code);
            util.printResponseInJSON(currency, response);
        } catch (CurrencyNotFoundException | DbDontWorkException | CodeInvalidException e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
