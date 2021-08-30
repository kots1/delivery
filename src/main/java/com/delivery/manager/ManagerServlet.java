package com.delivery.manager;

import com.delivery.db.TariffDAO;
import com.delivery.db.TransportDAO;
import com.delivery.entity.Tariff;
import com.delivery.entity.Transport;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tariff> tariffs = TariffDAO.getInstance().getAllTariff();
        List<Transport> transports = TransportDAO.getInstance().getAllTransport();
        req.getSession().setAttribute("transports",transports);
        req.getSession().setAttribute("tariffs",tariffs);
        req.getRequestDispatcher("/manager/manager.jsp").forward(req,resp);
    }
}
