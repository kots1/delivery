package com.delivery.servlet;

import com.delivery.Calculator;
import com.delivery.db.DirectionDAO;
import com.delivery.db.OrderDao;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.*;

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
import java.util.stream.Collectors;

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
        Map<String, String[]> parameterMap = req.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue()[0]);
        }

        if (step == 4) {
            double weight = Double.parseDouble(req.getParameter("weight"));
            double volume = Double.parseDouble(req.getParameter("volume"));
            int directionId = Integer.parseInt(req.getParameter("direction"));
            int typeId = Integer.parseInt(req.getParameter("type"));
            int tariffId = Integer.parseInt(req.getParameter("tariff"));
            System.out.println(directionId + "+" + tariffId);
            double price = Calculator.calculatePrice(directionId, tariffId, typeId, weight, volume);
            Date receivingDate = Calculator.calculateDate(directionId, tariffId);

            SimpleDateFormat dateFormat = new SimpleDateFormat();
            req.setAttribute("price", price);
            req.setAttribute("receivingDate", dateFormat.format(receivingDate));
        }
        if (step == 5) {
            int directionId = Integer.parseInt(req.getParameter("direction"));
            int tariffId = Integer.parseInt(req.getParameter("tariff"));
            int typeId = Integer.parseInt(req.getParameter("type"));

            Tariff tariff = TariffDAO.getInstance().getTariffById(tariffId);
            Direction direction = DirectionDAO.getInstance().getDirectionById(directionId);
            TypeBaggage typeBaggage = TypeBaggageDAO.getInstance().getTypeById(typeId);

            User user = (User) req.getSession().getAttribute("user");
            Order order = new Order.Builder()
                    .weight(Double.parseDouble(req.getParameter("weight")))
                    .user(user)
                    .apartment(Integer.parseInt(req.getParameter("apartment")))
                    .house(Integer.parseInt(req.getParameter("house")))
                    .street(req.getParameter("street"))
                    .description(req.getParameter("desc"))
                    .tariff(tariff)
                    .typeBaggage(typeBaggage)
                    .direction(direction)
                    .price(Double.parseDouble(req.getParameter("price")))
                    .volume(Double.parseDouble(req.getParameter("volume")))
                    .build();
            if (!OrderDao.getInstance().insertOrder(order)) {
                req.setAttribute("loginError", "Даного користувача не існує");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/successOrder.jsp");

            return;
        }

        double weight = Double.parseDouble(req.getParameter("weight"));
        double volume = Double.parseDouble(req.getParameter("volume"));


        List<Tariff> tariffs = TariffDAO.getInstance().getAllAliveTariff();
        List<Direction> directions = DirectionDAO.getInstance().getAllAliveDirection();
        tariffs = tariffs.stream()
                .filter(t -> t.getMaxVolume() >= volume && t.getMaxWeight() >= weight)
                .collect(Collectors.toList());
        List<TypeBaggage> types = TypeBaggageDAO.getInstance().getAllTypes();


        req.setAttribute("tariffs", tariffs);
        req.setAttribute("directions", directions);
        req.setAttribute("types", types);


        req.setAttribute("step", step);
        req.getRequestDispatcher("/createOrder.jsp").forward(req, resp);
    }
}
