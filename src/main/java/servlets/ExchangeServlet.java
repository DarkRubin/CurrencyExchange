package servlets;

import DTO.ExceptionDTO;
import DTO.ExchangeDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Exchange;
import service.ExchangeService;

import java.io.IOException;

import static mapper.ExchangeMapper.EXCHANGE_MAPPER;

@WebServlet(name = "ExchangeServlet", value = "/exchange/*")
public class ExchangeServlet extends StartServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExchangeService service = new ExchangeService();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        double amount = Double.parseDouble(request.getParameter("amount"));
        try {
            Exchange exchange = service.calculateExchange(from, to, amount);
            ExchangeDTO exchangeDTO = EXCHANGE_MAPPER.toDTO(exchange);
            util.printResponseInJSON(exchangeDTO, response);
        } catch (ExceptionDTO e) {
            response.setStatus(e.getHttpCode());
            util.printResponseInJSON(e.getMessage(), response);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
