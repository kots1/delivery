package com.delivery.servlet.manager;

import com.delivery.PaginationBuilder;
import com.delivery.db.TariffDAO;
import com.delivery.entity.Tariff;
import com.delivery.listener.ConfigListener;
import com.delivery.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/tariff")
public class TariffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        PaginationBuilder paginationBuilder;
        paginationBuilder = new PaginationBuilder(page, TariffDAO.getInstance().getAllAliveTariff().size());
        paginationBuilder.setCountOnPage(4);
        List<Tariff> tariffs = TariffDAO.getInstance().getAllTariffWithLimit(paginationBuilder.getStartElement(), paginationBuilder.getCountOnPage());

        req.setAttribute("tariffs", tariffs);
        req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        req.setAttribute("currentPage", paginationBuilder.getPage());
        req.setAttribute("part", req.getParameter("part"));
        req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pricePerKm = Integer.parseInt(req.getParameter("priceKm"));
        int pricePerM3 = Integer.parseInt(req.getParameter("priceM3"));
        int pricePerKg = Integer.parseInt(req.getParameter("priceKg"));
        int maxKg = Integer.parseInt(req.getParameter("maxKg"));
        int maxM3 = Integer.parseInt(req.getParameter("maxM3"));
        int timePer100km = Integer.parseInt(req.getParameter("time"));

        Tariff tariff = new Tariff.Builder()
                .pricePerKg(pricePerKg)
                .maxWeight(maxKg)
                .maxVolume(maxM3)
                .pricePerM3(pricePerM3)
                .pricePerKm(pricePerKm)
                .timePer100km(timePer100km)
                .build();
        Map<String, String> localesInfo = new HashMap<>();
        for (String locale : ConfigListener.getLocales()) {
            String name = req.getParameter("name_" + locale);
            if (name == null) {
                continue;
            }
            localesInfo.put(locale, name);
        }

        try {
            TariffService.insertTariff(tariff, localesInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

        resp.sendRedirect("manager?part=tariff");
    }

}
