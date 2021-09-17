package com.delivery.servlet.manager;

import com.delivery.listener.ConfigListener;
import com.delivery.db.TariffDAO;
import com.delivery.entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tariff")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pricePerKm =Integer.parseInt(req.getParameter("priceKm"));
        int pricePerM3 =Integer.parseInt(req.getParameter("priceM3"));
        int pricePerKg =Integer.parseInt(req.getParameter("priceKg"));
        int maxKg =Integer.parseInt(req.getParameter("maxKg"));
        int maxM3 =Integer.parseInt(req.getParameter("maxM3"));
        int timePer100km =Integer.parseInt(req.getParameter("time"));
        Tariff tariff = new Tariff.Builder()
                .pricePerKg(pricePerKg)
                .maxWeight(maxKg)
                .maxVolume(maxM3)
                .pricePerM3(pricePerM3)
                .pricePerKm(pricePerKm)
                .timePer100km(timePer100km)
                .build();
        if (!TariffDAO.getInstance().insertTariff(tariff)) {
            resp.sendRedirect("/error.jsp");
        }
        System.out.println(tariff);
        List<String> locales = ConfigListener.getLocales();
        for (String locale: locales){
            String name = req.getParameter("name_"+locale);
            if (name==null){
                continue;
            }
            if (!TariffDAO.getInstance().insertLocaleTariffName(locale,name,tariff)) {
                req.setAttribute("errorMessage","cannot insert "+locale+" name");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }
        }
        resp.sendRedirect("manager");
    }
}
