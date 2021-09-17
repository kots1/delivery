package com.delivery.servlet;

import com.delivery.db.OrderDao;
import com.delivery.entity.Order;
import com.delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = OrderDao.getInstance().getOrderByUserId(user.getId());
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/profile.jsp").forward(req,resp);
    }
}
