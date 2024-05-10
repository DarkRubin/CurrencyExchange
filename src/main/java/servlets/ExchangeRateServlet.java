package servlets;

import DTO.ExchangeRateDTO;
import exceptions.Service.CodePairInvalidException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import exceptions.Service.ExchangeRateNotFoundException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ExchangeRatesService;

import java.io.IOException;

@WebServlet(name = "ExchangeRateServlet", value = "/exchangeRate/*")
public class ExchangeRateServlet extends StartServlet implements AddressReader {

    final ExchangeRatesService service = new ExchangeRatesService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CurrencyPair currencyPair = AddressReader.getCurrencyPair(request);
            ExchangeRateDTO toSearch = service.codsToDTO(currencyPair.base, currencyPair.target, 0);
            printResponse(gson.toJson(service.findInTable(toSearch)), response);
        } catch (CurrencyNotFoundException | DbDontWorkException | ExchangeRateNotFoundException |
                 CodePairInvalidException e) {
            response.setStatus(e.getHttpCode());
            printResponse(e.getMessage(), response);
        }
    }


    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {
    }
}
