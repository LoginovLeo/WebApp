package controller;

import services.DBService.DBException;
import services.DBService.dataSets.MessageDataSet;
import services.mesages.MessageService;
import services.users.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        MessageService messageService = new MessageService();
        resp.setContentType(("text/html;charset=utf-8"));
        try {
            List<MessageDataSet> tag = messageService.getMessagesByTag(req.getParameter("tag"));
            req.setAttribute("FilterMessage", tag);
        } catch (DBException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/mainPage");
       /* RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/mainPage.jsp");
        requestDispatcher.forward(req, resp);*/
        resp.setStatus(HttpServletResponse.SC_OK);


    }
}
