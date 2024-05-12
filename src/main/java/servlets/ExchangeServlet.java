package servlets;

import DTO.Exchange;
import exceptions.Service.ServiceException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ExchangeService;

import java.io.IOException;

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
            printResponseInJSON(exchange, response);
        } catch (ServiceException e) {
            response.setStatus(e.getHttpCode());
            printResponseInJSON(e.getMessage(), response);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
