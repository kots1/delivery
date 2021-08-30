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
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> list= UserDAO.getInstance().findAllUsers();
        request.setAttribute("users", list);
         request.getRequestDispatcher("/output.jsp").forward(request, response);

    }


    public void destroy() {
    }
}