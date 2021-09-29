package com.delivery.filter;

import com.delivery.CurrentLocale;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getParameter("lang") != null) {
            String lang= req.getParameter("lang");
            req.getSession().setAttribute("lang",lang);
            CurrentLocale.setLocale(lang);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {}

    public void init(FilterConfig arg0) throws ServletException {}

}
