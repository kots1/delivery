package com.delivery.servlet;

import com.delivery.CurrentLocale;
import com.delivery.PaginationBuilder;
import com.delivery.Resources;
import com.delivery.db.DirectionDAO;
import com.delivery.db.TariffDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Tariff;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Direction> directions;
        String[] finalCity=  request.getParameterValues("final");
        String[] startCity=  request.getParameterValues("start");

        PaginationBuilder paginationBuilder = new PaginationBuilder(request.getParameter("page"),DirectionDAO.getInstance().getDirectionFilterCitiesCount(finalCity, startCity));
        directions = DirectionDAO.getInstance().getDirectionFilterCities(finalCity, startCity,paginationBuilder.getStartElement(),paginationBuilder.getCountOnPage() );

        request.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        request.setAttribute("currentPage", paginationBuilder.getPage());

        List<Tariff> tariffs = TariffDAO.getInstance().getAllAliveTariffWithLimit(1,2);
        request.setAttribute("directions",directions);
        request.setAttribute("distinctFinalCities",DirectionDAO.getInstance().getDistinctDirectionFinalCity());
        request.setAttribute("distinctStartCities",DirectionDAO.getInstance().getDistinctDirectionStartCity());
        request.setAttribute("tariffs",tariffs);
         request.getRequestDispatcher("/start.jsp").forward(request, response);

    }

}