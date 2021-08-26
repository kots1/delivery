package com.delivery.Auth;

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
        req.getRequestDispatcher("/output.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String login =req.getParameter("login");
        String name =req.getParameter("name");
        String secondName =req.getParameter("secondName");
        String password =req.getParameter("password");
        String email =req.getParameter("email");
        System.out.println(name);
        User user;
        try {
            user =Authentication.register(login,name,secondName,password,email);
            req.getSession().setAttribute("user",user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("login",name);
        resp.sendRedirect("index.jsp");
    }
}
