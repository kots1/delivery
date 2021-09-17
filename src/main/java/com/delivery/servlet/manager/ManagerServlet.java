package com.delivery.servlet.manager;

import com.delivery.db.DirectionDAO;
import com.delivery.db.OrderDao;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Order;
import com.delivery.entity.Tariff;
import com.delivery.entity.TypeBaggage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tariff> tariffs = TariffDAO.getInstance().getAllTariff();
        List<Direction> directions = DirectionDAO.getInstance().getAllDirection();
        List<Order> orders = OrderDao.getInstance().getAllOrder();
        List<TypeBaggage> types = TypeBaggageDAO.getInstance().getAllTypes();
        req.setAttribute("tariffs",tariffs);
        req.setAttribute("directions",directions);
        req.setAttribute("orders",orders);
        req.setAttribute("types",types);
        req.getRequestDispatcher("/manager/manager.jsp").forward(req,resp);
    }
}
