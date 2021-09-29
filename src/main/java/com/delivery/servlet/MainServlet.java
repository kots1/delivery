package com.delivery.servlet;

import com.delivery.ConvertToPDF;
import com.delivery.db.DirectionDAO;
import com.delivery.db.TariffDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Tariff;
import com.delivery.entity.User;
import com.delivery.db.UserDAO;

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
        int page=  1 ;
        int total=  3 ;
       if (request.getParameter("page")!=null){
           page = Integer.parseInt(request.getParameter("page"));
       }

        directions = DirectionDAO.getInstance().getDirectionFilterCities(finalCity, startCity,(page-1)*total+1,total );
        int row = DirectionDAO.getInstance().getDirectionFilterCitiesCount(finalCity, startCity);
        int nOfPages = row  / total;
        if (row % total > 0) {
            nOfPages++;
        }
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", page);

        List<Tariff> tariffs = TariffDAO.getInstance().getAllAliveTariff();
        request.setAttribute("directions",directions);
        request.setAttribute("distinctFinalCities",DirectionDAO.getInstance().getDistinctDirectionFinalCity());
        request.setAttribute("distinctStartCities",DirectionDAO.getInstance().getDistinctDirectionStartCity());
        request.setAttribute("tariffs",tariffs.subList(0,2));

         request.getRequestDispatcher("/start.jsp").forward(request, response);

    }

}