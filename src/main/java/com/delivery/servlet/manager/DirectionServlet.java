package com.delivery.servlet.manager;

import com.delivery.entity.Direction;
import com.delivery.listener.ConfigListener;
import com.delivery.service.DirectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/direction")
public class DirectionServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int distance = Integer.parseInt(req.getParameter("distance"));
        Direction direction = new Direction(distance);

        Map<String, String[]> localesInfo = new HashMap<>();
        for (String locale : ConfigListener.getLocales()) {

            String startCity = req.getParameter("start_" + locale);
            String finalCity = req.getParameter("final_" + locale);
            if (startCity == null || finalCity == null) {
                continue;
            }
            String[] cities = {startCity, finalCity};
            localesInfo.put(locale, cities);
        }

        try {
            DirectionService.insertDirection(direction, localesInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

        resp.sendRedirect("manager?part=direction");

    }

}
