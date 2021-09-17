package com.delivery.servlet.manager;

import com.delivery.listener.ConfigListener;
import com.delivery.db.DirectionDAO;
import com.delivery.entity.Direction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/direction")
public class DirectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int distance =Integer.parseInt(req.getParameter("distance"));
        Direction direction = new Direction(distance);
        if (!DirectionDAO.getInstance().insertDirection(direction)) {
            req.setAttribute("errorMessage","cannot insert direction");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
        List<String> locales = ConfigListener.getLocales();
        for (String locale: locales){
            String startCity = req.getParameter("start_"+locale);
            String finalCity = req.getParameter("final_"+locale);
            if (startCity==null || finalCity==null){
                continue;
            }
            if (!DirectionDAO.getInstance().insertLocaleDirections(locale,direction,startCity,finalCity)) {
                req.setAttribute("errorMessage","cannot insert "+locale+" name");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }
        }
        resp.sendRedirect("manager");

    }
}
