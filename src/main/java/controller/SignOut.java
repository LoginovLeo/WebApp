package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("You logout");
        req.getSession().removeAttribute("Logged USER");
        HttpSession session = req.getSession(false);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/signOut.jsp");
        requestDispatcher.forward(req, resp);

    }
}