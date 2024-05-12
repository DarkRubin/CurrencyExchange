package servlets;

import DTO.ExchangeRateDTO;
import exceptions.Service.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Currency;
import service.ExchangeRatesService;

import java.io.IOException;

@WebServlet(name = "ExchangeRatesServlet", value = "/exchangeRates/*")
public class ExchangeRatesServlet extends StartServlet implements AddressReader {

    private final ExchangeRatesService service = new ExchangeRatesService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Currency base = new Currency(request.getParameter("baseCurrencyCode"));
        Currency target = new Currency(request.getParameter("targetCurrencyCode"));
        double rate = Double.parseDouble(request.getParameter("rate"));
        ExchangeRateDTO dto = new ExchangeRateDTO(base, target, rate);
        saveAndPrint(response, dto);
    }

    private void saveAndPrint(HttpServletResponse response, ExchangeRateDTO dto) throws IOException {
        try {
            response.setStatus(201);
            printResponseInJSON(service.saveToTable(dto), response);
        } catch (CurrencyNotFoundException | DbDontWorkException | ExchangeRateNotFoundException e) {
            response.setStatus(e.getHttpCode());
            printResponseInJSON(e.getMessage(), response);
        } catch (ExchangeRateAlreadyExistException e) {
            response.setStatus(e.getHttpCode());
            printResponseInJSON(e.savedExchangeRate, response);
        }

    }
    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            double rate = Double.parseDouble(request.getParameter("rate"));
            CurrencyPair currencyPair = AddressReader.getCurrencyPair(request);
            ExchangeRateDTO dto = service.codsToDTO(currencyPair.base, currencyPair.target, rate);
            printResponseInJSON(service.updateInTable(dto), response);
        } catch (DbDontWorkException | CurrencyNotFoundException | ExchangeRateNotFoundException |
                 CodePairInvalidException e) {
            response.setStatus(e.getHttpCode());
            printResponseInJSON(e.getMessage(), response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            printResponseInJSON(service.findAllInTable(), response);
        } catch (CurrencyNotFoundException | DbDontWorkException e) {
            response.setStatus(e.getHttpCode());
            printResponseInJSON(e.getMessage(), response);
        }
    }
}
