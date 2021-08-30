package com.delivery.manager;

import com.delivery.Auth.Authentication;
import com.delivery.db.TransportDAO;
import com.delivery.entity.Transport;
import com.delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/transport")
public class TransportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transport> listOfTransport = TransportDAO.getInstance().getAllTransport();
        req.setAttribute("transports",listOfTransport);
        req.getRequestDispatcher("/manager/transport.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String description =req.getParameter("desc");
        System.out.println(description);
        int maxKg =Integer.parseInt(req.getParameter("kg"));
        int maxM3 =Integer.parseInt(req.getParameter("m3"));
        int timePer100km =Integer.parseInt(req.getParameter("time"));
        Transport transport;
        transport = Transport.createTransport(description,maxKg,maxM3,timePer100km);
        if (!TransportDAO.getInstance().insertTransport(transport)) {
            resp.sendRedirect("/error.jsp");
            System.out.println("err");
        }
        resp.sendRedirect("manager");
    }
}
