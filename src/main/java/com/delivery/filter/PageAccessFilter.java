package com.delivery.filter;

import com.delivery.entity.Role;
import com.delivery.entity.User;
import com.sun.applet2.AppletParameters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PageAccessFilter implements Filter {


    private Map<Role,List<String>> accessMap;
    private List<String> outOfControl;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (accessAllowed(request)){
            chain.doFilter(request, response);
        }
        else {
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String[] urlPart = httpRequest.getRequestURI().split("/");
        String servletName = urlPart[urlPart.length-1];
        if (servletName.isEmpty()){
            return true;
        }
        Role role = (Role) httpRequest.getSession().getAttribute("role");

        if (accessMap.get(Role.MANAGER).contains(servletName) && role!=Role.MANAGER) {
            return false;
        }
        return !accessMap.get(Role.CLIENT).contains(servletName) || (!(role != Role.CLIENT & role != Role.MANAGER));
    }

    public void init(FilterConfig fConfig) throws ServletException {

        // roles
        accessMap = new HashMap<>();
        accessMap.put(Role.MANAGER, asList(fConfig.getInitParameter("manager")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("user")));


    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
