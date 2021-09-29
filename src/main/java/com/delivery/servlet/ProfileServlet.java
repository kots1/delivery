package com.delivery.servlet;

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
        int page = 1;
        int total = 2;
        int nOfPages;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int row = OrderDao.getInstance().getOrderCountById(user.getId());
        List<Order> orders = OrderDao.getInstance().getOrderByUserId(user.getId(), (page - 1) * total + 1, total);
        nOfPages = row / total;
        if (row % total > 0) {
            nOfPages++;
        }

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
