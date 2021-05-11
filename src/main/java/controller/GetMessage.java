package controller;

import services.DBService.DBException;
import services.DBService.dataSets.MessageDataSet;
import services.users.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        try {
            List<MessageDataSet> tag = userService.getMessagesByTag(req.getParameter("tag"));
            for (MessageDataSet messageDataSet : tag) {
                resp.getWriter().println(messageDataSet.getMessage() + " " + messageDataSet.getMessageTag() + " " + messageDataSet.getUserLogin());
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        resp.setContentType(("text/html;charset=utf-8"));
        resp.setStatus(HttpServletResponse.SC_OK);


    }
}
