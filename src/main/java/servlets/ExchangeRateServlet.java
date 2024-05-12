package servlets;

import DTO.CurrencyPair;
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
public class ExchangeRateServlet extends StartServlet {

    private final ExchangeRatesService service = new ExchangeRatesService();
    private final Util util = new Util();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CurrencyPair currencyPair = util.getCurrencyPairFromUrl(request);
            ExchangeRateDTO toSearch = service.codsToDTO(currencyPair.base(), currencyPair.target(), 0);
            util.printResponseInJSON(service.findInTable(toSearch), response);
        } catch (CurrencyNotFoundException | DbDontWorkException | ExchangeRateNotFoundException |
                 CodePairInvalidException e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }


    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {
    }
}
