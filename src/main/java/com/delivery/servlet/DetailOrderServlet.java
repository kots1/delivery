package com.delivery.servlet;

import com.delivery.db.OrderDao;
import com.delivery.entity.Order;
import com.delivery.entity.Role;
import com.delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail")
public class DetailOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("orderId") == null) {
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
            return;
        }
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order order = OrderDao.getInstance().getOrderById(orderId);
        User user = (User) req.getSession().getAttribute("user");
        if (order.getUser().getId() != user.getId() && Role.getRole(user)!=Role.MANAGER) {
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
            return;
        }
        req.setAttribute("order",order);
        req.getRequestDispatcher("/detail.jsp").forward(req,resp);
    }
}
