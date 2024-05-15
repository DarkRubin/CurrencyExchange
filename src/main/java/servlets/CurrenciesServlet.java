package servlets;

import DAO.CurrencyTable;
import exceptions.Service.CurrencyAlreadyExistExceptionDTO;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import exceptions.Service.NeedFieldEmptyExceptionDTO;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> fields = new ArrayList<>();
        fields.add(request.getParameter("name"));
        fields.add(request.getParameter("code"));
        fields.add(request.getParameter("sign"));
        try {
            util.isNotEmpty(fields);
            Currency currency = new Currency(fields.get(1), fields.get(0), fields.get(2));
            response.setStatus(201);
            util.printResponseInJSON(service.save(currency), response);
        } catch (NeedFieldEmptyExceptionDTO | DbDontWorkExceptionDTO | CurrencyNotFoundExceptionDTO |
                 CurrencyAlreadyExistExceptionDTO e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CurrencyTable table = new CurrencyTable();
            List<Currency> currencies = table.findAll();
            util.printResponseInJSON(currencies, response);
        } catch (DbDontWorkExceptionDTO e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
