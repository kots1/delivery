package com.delivery.servlet;

import com.delivery.Auth.Authentication;
import com.delivery.entity.Role;
import com.delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/indexindex.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login =req.getParameter("login");
        String name =req.getParameter("name");
        String secondName =req.getParameter("secondName");
        String password =req.getParameter("password");
        String email =req.getParameter("email");
        String phone =req.getParameter("phone");
        User user;
        try {
            user = Authentication.register(login,name,secondName,password,email,phone);
            req.getSession().setAttribute("user",user);
            req.getSession().setAttribute("role", Role.getRole(user).getName());
            System.out.println(Role.getRole(user).getName());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("login",name);
        resp.sendRedirect(req.getContextPath()+"/");
    }
}
