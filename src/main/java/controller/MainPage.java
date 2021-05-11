package controller;

import services.DBService.DBException;
import services.DBService.dataSets.MessageDataSet;
import services.DBService.dataSets.UsersDataSet;
import services.users.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        try {
            List<UsersDataSet> users = userService.getUsers();
            req.setAttribute("ListOfUser", users);
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            List<MessageDataSet> messages = userService.getMessages();
            req.setAttribute("Messages", messages);
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            List<MessageDataSet> tag = userService.getMessagesByTag(req.getParameter("tag"));
            req.setAttribute("FilterMessage", tag);
        } catch (DBException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/mainPage.jsp");
        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("message") == null || req.getParameter("message").isEmpty()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
            UserService userService = new UserService();
            req.getParameter("message");
            try {
                userService.addMessage(req.getParameter("message"), req.getParameter("tag"), (String) req.getSession().getAttribute("Login"));
            } catch (DBException e) {
                e.printStackTrace();
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("mainPage");

    }
}