package com.delivery.servlet;

import com.delivery.db.DirectionDAO;
import com.delivery.db.TariffDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Tariff;
import com.delivery.entity.User;
import com.delivery.db.UserDAO;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Direction> directionList = DirectionDAO.getInstance().getAllAliveDirection();
       List<Tariff> tariffs = TariffDAO.getInstance().getAllAliveTariff();
        request.setAttribute("directions",directionList);
        request.setAttribute("tariffs",tariffs);
         request.getRequestDispatcher("/start.jsp").forward(request, response);

    }


    public void destroy() {
    }
}