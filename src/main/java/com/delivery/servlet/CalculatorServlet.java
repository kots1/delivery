package com.delivery.servlet;

import com.delivery.Calculator;
import com.delivery.db.DirectionDAO;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Tariff;
import com.delivery.entity.TypeBaggage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    public static int step;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("step");

        if (act == null) {
            step = 0;
            req.setAttribute("step", step);
            req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
            return;
        }
        step = step+Integer.parseInt(act);

        if (step < 0 || step > 2) {
            step = 0;
            req.setAttribute("step", step);
            req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
            return;
        }

        Map<String,String[]> parameterMap=req.getParameterMap();

        for (Map.Entry<String,String[]> entry: parameterMap.entrySet()){
            req.setAttribute(entry.getKey(),entry.getValue()[0]);
        }


        double weight = Double.parseDouble(req.getParameter("weight"));
        double volume = Double.parseDouble(req.getParameter("volume"));
        List<Tariff> tariffs = TariffDAO.getInstance().getAllAliveTariff();
        List<Direction> directions = DirectionDAO.getInstance().getAllAliveDirection();
        tariffs = tariffs.stream()
                .filter(t -> t.getMaxVolume() >= volume && t.getMaxWeight() >= weight)
                .collect(Collectors.toList());
        List<TypeBaggage> types = TypeBaggageDAO.getInstance().getAllTypes();


        req.setAttribute("tariffs", tariffs);
        req.setAttribute("directions", directions);
        req.setAttribute("types",types);



        if (step == 2) {
            if (req.getParameter("tariff")==null){
                step=1;
                req.setAttribute("step", step);
                req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
            }
            int directionId = Integer.parseInt(req.getParameter("direction"));
            int tariffId = Integer.parseInt(req.getParameter("tariff"));
            int typeId = Integer.parseInt(req.getParameter("type"));
            double price = Calculator.calculatePrice(directionId, tariffId,typeId, weight, volume);
            Date receivingDate = Calculator.calculateDate(directionId, tariffId);
            SimpleDateFormat dateFormat =new SimpleDateFormat();
            req.setAttribute("price", price);
            req.setAttribute("tariff", tariffId);
            req.setAttribute("direction", directionId);
            req.setAttribute("receivingDate", dateFormat.format( receivingDate ) );
        }
        req.setAttribute("step", step);
        req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
    }
}
