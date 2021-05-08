package controller;

import services.DBService.DBException;
import services.users.UserProfile;
import services.users.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignIn extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("Logged USER") != null){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/mainPage");
            requestDispatcher.forward(req, resp);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/signIn.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("login") == null || req.getParameter("pass") == null || req.getParameterMap().size() > 2) {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("Incorrect request. Please enter \"login\" and \"pass\"");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = null;
        try {
            userProfile = userService.getUserByLoginPass(req.getParameter("login"), req.getParameter("pass"));
        } catch (DBException e) {
            e.printStackTrace();
        } if (userProfile == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
            return;
        }
        userService.addSession(req.getSession().getId(),userProfile);
        req.getSession().setAttribute("Logged USER",userProfile);

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/mainPage");
        requestDispatcher.forward(req, resp);

    }
}
