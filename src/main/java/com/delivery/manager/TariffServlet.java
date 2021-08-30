package com.delivery.manager;

import com.delivery.db.TariffDAO;
import com.delivery.db.TransportDAO;
import com.delivery.entity.Tariff;
import com.delivery.entity.Transport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tariff")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name =req.getParameter("name");
        int pricePerKm =Integer.parseInt(req.getParameter("kg"));
        int pricePerM3 =Integer.parseInt(req.getParameter("m3"));
        int pricePerKg =Integer.parseInt(req.getParameter("kg"));
        int id_transport =Integer.parseInt(req.getParameter("transport"));
        System.out.println(id_transport);
        Transport transport = TransportDAO.getInstance().getTransportById(id_transport);
        System.out.println(transport);
        Tariff tariff;
        tariff = Tariff.createTariff(name,pricePerKm,pricePerM3,pricePerKg,transport);
        if (!TariffDAO.getInstance().insertTariff(tariff)) {
            resp.sendRedirect("/error.jsp");
        }
        resp.sendRedirect("manager");
    }
}
