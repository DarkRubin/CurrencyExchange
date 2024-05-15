package servlets;

import DTO.CurrencyPair;
import DTO.ExchangeRateDTO;
import exceptions.Service.CodePairInvalidExceptionDTO;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import exceptions.Service.ExchangeRateNotFoundExceptionDTO;
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
            CurrencyPair currencyPair = util.getCurrencyPairFromPatch(request);
            ExchangeRateDTO toSearch = service.codsToDTO(currencyPair.base, currencyPair.target, 0);
            util.printResponseInJSON(service.find(toSearch), response);
        } catch (CurrencyNotFoundExceptionDTO | DbDontWorkExceptionDTO | ExchangeRateNotFoundExceptionDTO |
                 CodePairInvalidExceptionDTO e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }


    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {
    }
}
