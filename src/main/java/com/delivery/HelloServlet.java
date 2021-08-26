package com.delivery;

import com.delivery.entity.User;
import com.delivery.db.UserDAO;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "users", value = "/users")
public class HelloServlet extends HttpServlet {

    public void init() {
        System.out.println("init");
        System.out.println(Config.getDataSource());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("test!");
        List<User> list= UserDAO.getInstance().findAllUsers();
        System.out.println(list);
        System.out.println( request.getSession().getId());
        request.setAttribute("users", list);
         request.getRequestDispatcher("/output.jsp").forward(request, response);

    }


    public void destroy() {
    }
}