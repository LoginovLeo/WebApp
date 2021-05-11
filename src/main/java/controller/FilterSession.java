package controller;

import services.users.UserProfile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterSession implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse) servletResponse;
        HttpSession session = httpReq.getSession();
        UserProfile currentUser = (UserProfile) session.getAttribute("Logged USER");
        if (currentUser == null) {
            servletResponse.getWriter().println("Unauthorized");
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpRes.sendRedirect("/signIn");//redirect to LoginServlet
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}