package controller;


import services.DBService.DBException;
import services.users.UserProfile;
import services.users.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUp extends HttpServlet {
    private final UserService userService = new UserService();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Hallo World");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("e-mail");


        if(login == null || pass == null || email == null || req.getParameterMap().size() > 3){
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("Incorrect request. Please enter \"login\" , \"pass\" and \"email\"");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {

            userService.addNewUser(new UserProfile(login,pass,email));
        } catch (DBException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("User add");
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
