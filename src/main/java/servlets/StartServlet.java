package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class StartServlet extends HttpServlet {

    protected Util util = new Util();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req,resp);
        } else {
            super.service(req, resp);
        }

    }

    protected abstract void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
