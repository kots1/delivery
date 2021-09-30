package com.delivery.servlet.manager;

import com.delivery.PDFManager;
import com.delivery.PaginationBuilder;
import com.delivery.db.DirectionDAO;
import com.delivery.db.OrderDao;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Order;
import com.delivery.entity.Tariff;
import com.delivery.entity.TypeBaggage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String part = req.getParameter("part");
        if (part==null){
            part="order";
        }
        PaginationBuilder paginationBuilder = new PaginationBuilder(req.getParameter("page"));
        List<Direction> directions =null;
        List<Tariff> tariffs=null;
        List<Order> orders=null;
        List<TypeBaggage> types= null;

        switch (part) {
            case "direction":
                paginationBuilder.setCountOfElement(DirectionDAO.getInstance().getCount());
                directions = DirectionDAO.getInstance().getAllDirectionWithLimit(paginationBuilder.getStartElement(), paginationBuilder.getCountOnPage());
                break;
            case "tariff":
                paginationBuilder.setCountOfElement(TariffDAO.getInstance().getCount());
                tariffs = TariffDAO.getInstance().getAllTariffWithLimit(paginationBuilder.getStartElement(), paginationBuilder.getCountOnPage());
                break;
            case "order":
                directions= DirectionDAO.getInstance().getAllDirection();
                String chosenDate = req.getParameter("orderDate");
                int[] directionId = null;
                if (req.getParameterValues("directionId") != null) {
                    directionId = Stream.of(req.getParameterValues("directionId"))
                            .mapToInt(Integer::parseInt).toArray();
                }
                paginationBuilder.setCountOfElement(OrderDao.getInstance().getAllOrderWithFilter(chosenDate, directionId).size());
                orders = OrderDao.getInstance().getAllOrderWithFilterWithLimit(chosenDate, directionId, paginationBuilder.getStartElement(), paginationBuilder.getCountOnPage());
                break;
            case "type":
                paginationBuilder.setCountOfElement(TypeBaggageDAO.getInstance().getCount());
                 types = TypeBaggageDAO.getInstance().getAllTypesWithLimit(paginationBuilder.getStartElement(), paginationBuilder.getCountOnPage());
                break;
            default:
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
        }

        req.setAttribute("types", types);
        req.setAttribute("orders", orders);
        req.setAttribute("tariffs", tariffs);
        req.setAttribute("directions",directions);
        req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        req.setAttribute("currentPage", paginationBuilder.getPage());
        req.setAttribute("part",part);


        req.getRequestDispatcher("/manager/manager.jsp").forward(req,resp);
    }
}
