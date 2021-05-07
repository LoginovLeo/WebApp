package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login") == null || req.getParameter("pass") == null || req.getParameterMap().size() > 2) {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("Incorrect request. Please enter \"login\" and \"pass\"");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
