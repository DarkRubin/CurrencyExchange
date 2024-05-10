package servlets;

import DAO.CurrencyTable;
import exceptions.Service.CurrencyAlreadyExistException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import exceptions.Service.NeedFieldEmptyException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Currency;
import service.CurrenciesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CurrenciesServlet", value = "/currencies")
public class CurrenciesServlet extends StartServlet {

    private final CurrenciesService service = new CurrenciesService();

    public void isValid(ArrayList<String> fields) throws NeedFieldEmptyException {
        for (String string : fields) {
            if (string.isEmpty()) {
                throw new NeedFieldEmptyException();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(request.getParameter("name"));
        fields.add(request.getParameter("code"));
        fields.add(request.getParameter("sign"));
        try {
            isValid(fields);
            Currency currency = new Currency(fields.get(1), fields.get(0), fields.get(2));
            response.setStatus(201);
            printResponse(gson.toJson(service.saveToTable(currency)), response);
        } catch (NeedFieldEmptyException | DbDontWorkException | CurrencyNotFoundException e) {
            response.setStatus(e.getHttpCode());
            printResponse(e.getMessage(), response);
        } catch (CurrencyAlreadyExistException e) {
            response.setStatus(e.getHttpCode());
            printResponse(gson.toJson(e.savedCurrency), response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CurrencyTable table = new CurrencyTable();
            List<Currency> currencies = table.findAll();
            printResponse(gson.toJson(currencies), response);
        } catch (DbDontWorkException e) {
            response.setStatus(e.getHttpCode());
            printResponse(e.getMessage(), response);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
