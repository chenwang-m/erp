package com.codingfuture.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null && !request.getRequestURI().contains("/login.html")) {
            response.sendRedirect("/login.html");
        } else {
            arg2.doFilter(arg0, arg1);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}