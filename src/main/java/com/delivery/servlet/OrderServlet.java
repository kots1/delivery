package com.delivery.servlet;

import com.delivery.Calculator;
import com.delivery.Resources;
import com.delivery.db.DirectionDAO;
import com.delivery.db.OrderDao;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Tariff;
import com.delivery.entity.TypeBaggage;
import com.delivery.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private int step = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("step", 0);
        req.getRequestDispatcher("/createOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("step");

        if (step > 5) {
            resp.sendRedirect("order");
            return;
        }
        step = step + Integer.parseInt(act);
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue()[0]);
        }

        if (step == 4) {
            double weight = Double.parseDouble(req.getParameter("weight"));
            double volume = Double.parseDouble(req.getParameter("volume"));
            int directionId = Integer.parseInt(req.getParameter("direction"));
            int typeId = Integer.parseInt(req.getParameter("type"));
            int tariffId = Integer.parseInt(req.getParameter("tariff"));
            double price = Calculator.calculatePrice(directionId, tariffId, typeId, weight, volume);
            Date receivingDate = Calculator.calculateDate(directionId, tariffId);
            SimpleDateFormat dateFormat = new SimpleDateFormat();

            req.setAttribute("price", price);
            req.setAttribute("receivingDate", dateFormat.format(receivingDate));
        }

        if (step == 5) {
            if (!OrderDao.getInstance().insertOrder(OrderService.getOrderFromRequestParam(req))) {
                req.setAttribute("errorMessage", Resources.getValue("error.order.noInsert"));
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/successOrder.jsp");
            return;
        }

        List<Tariff> tariffs = TariffDAO.getInstance().getAllAliveWeightVolume(Double.parseDouble(req.getParameter("weight")), Double.parseDouble(req.getParameter("volume")));
        List<Direction> directions = DirectionDAO.getInstance().getAllAliveDirection();
        List<TypeBaggage> types = TypeBaggageDAO.getInstance().getAllTypes();

        req.setAttribute("tariffs", tariffs);
        req.setAttribute("directions", directions);
        req.setAttribute("types", types);

        req.setAttribute("step", step);
        req.getRequestDispatcher("/createOrder.jsp").forward(req, resp);
    }
}
