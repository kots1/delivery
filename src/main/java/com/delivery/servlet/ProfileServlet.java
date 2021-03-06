package com.delivery.servlet;

import com.delivery.PaginationBuilder;
import com.delivery.db.DirectionDAO;
import com.delivery.db.OrderDao;
import com.delivery.entity.Order;
import com.delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        PaginationBuilder paginationBuilder = new PaginationBuilder(req.getParameter("page"), OrderDao.getInstance().getOrderCountById(user.getId()));
        List<Order> orders = OrderDao.getInstance().getOrderByUserId(user.getId(), paginationBuilder.getStartElement(), paginationBuilder.getCountOnPage());


        req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        req.setAttribute("currentPage", paginationBuilder.getPage());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
