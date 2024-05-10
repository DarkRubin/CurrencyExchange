package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StartServletServlet", value = "/start")
public abstract class StartServlet extends HttpServlet {


    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req,resp);
        } else {
            super.service(req, resp);
        }

    }

    protected abstract void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException;


    protected void printResponse(String toPrint, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(toPrint);
    }
}
