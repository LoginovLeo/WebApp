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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/mainPage.jsp");
        requestDispatcher.forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = new UserService();
        req.getParameter("message");
        try {
            userService.addMessage(req.getParameter("message"), req.getParameter("tag"), (String) req.getSession().getAttribute("Login"));
        } catch (DBException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("mainPage");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}