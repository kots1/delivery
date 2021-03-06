package com.delivery.servlet;

import com.delivery.Auth.Authentication;
import com.delivery.Auth.IllegalPasswordException;
import com.delivery.Resources;
import com.delivery.entity.Role;
import com.delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user ;
        try {
            user = Authentication.login(login,password);
            if (user==null){
                req.setAttribute("loginError", Resources.getValue("login.noUser"));
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                return;
            }
        }catch (IllegalPasswordException e){
            req.setAttribute("passwordError",e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        System.out.println( req.getSession().getId());
        req.getSession().setAttribute("user",user);
        req.getSession().setAttribute("role", Role.getRole(user));
        System.out.println(Role.getRole(user).getName());
        resp.sendRedirect(req.getContextPath()+"/");
    }
}
