package com.delivery.servlet.manager;

import com.delivery.ConvertToPDF;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String part = req.getParameter("part");
        if (part==null){
            part="order";
        }
        PaginationBuilder paginationBuilder;
        switch (part){
            case "direction":
                paginationBuilder = new PaginationBuilder(page,DirectionDAO.getInstance().getCount());
                List<Direction> directions = DirectionDAO.getInstance().getAllDirectionWithLimit(paginationBuilder.getStartElement(),paginationBuilder.getCountOnPage());
                req.setAttribute("directions",directions);
                break;
            case "tariff":
                paginationBuilder = new PaginationBuilder(page,TariffDAO.getInstance().getCount());
                List<Tariff> tariffs = TariffDAO.getInstance().getAllTariffWithLimit(paginationBuilder.getStartElement(),paginationBuilder.getCountOnPage());
                req.setAttribute("tariffs",tariffs);
                break;
            case "order":
                List<Direction> direction = DirectionDAO.getInstance().getAllDirection();
                String chosenDate=req.getParameter("orderDate");
                int[] directionId=null;
                if (req.getParameterValues("directionId")!=null) {
                     directionId = Stream.of(req.getParameterValues("directionId"))
                            .mapToInt(Integer::parseInt).toArray();
                }
                if (chosenDate==null||chosenDate.isEmpty()){
                    chosenDate=null;
                }
                paginationBuilder = new PaginationBuilder(page,OrderDao.getInstance().getAllOrderWithFilter(chosenDate,directionId).size());
                List<Order> orders = OrderDao.getInstance().getAllOrderWithFilterWithLimit(chosenDate,directionId,paginationBuilder.getStartElement(),paginationBuilder.getCountOnPage());
                req.setAttribute("orders",orders);
                req.setAttribute("orderDate",chosenDate);

                req.setAttribute("directions",direction);
                break;
            case "type":
                paginationBuilder = new PaginationBuilder(page,TypeBaggageDAO.getInstance().getCount());
                List<TypeBaggage> types = TypeBaggageDAO.getInstance().getAllTypesWithLimit(paginationBuilder.getStartElement(),paginationBuilder.getCountOnPage());
                req.setAttribute("types",types);
                break;
            default:
                req.setAttribute("errorMessage","cannot find page");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
        }

        req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        req.setAttribute("currentPage", paginationBuilder.getPage());
        req.setAttribute("part",part);

        if (req.getParameter("href")!=null){
            ServletContext context = req.getSession().getServletContext();

            String path = context.getRealPath("report.pdf");
            String chosenDate=req.getParameter("orderDate");
            int[] directionId=null;
            if (req.getParameterValues("directions")!=null) {
                directionId = Stream.of(req.getParameterValues("directions"))
                        .mapToInt(Integer::parseInt).toArray();
            }
            List<Order> orderList = OrderDao.getInstance().getAllOrderWithFilter(chosenDate,directionId);
            ConvertToPDF.generatePDF(path,orderList);
            req.setAttribute("href", path);
        }
        req.getRequestDispatcher("/manager/manager.jsp").forward(req,resp);
    }
}
