package com.delivery.servlet.manager;

import com.delivery.db.TariffDAO;
import com.delivery.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateTariff")
public class UpdateTariffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("delete") != null) {
                TariffService.delete(Integer.parseInt(req.getParameter("delete")));
            }
            if (req.getParameter("alive") != null) {
                String[] command = req.getParameter("alive").split("_");
                TariffDAO.getInstance().updateIsAlive(Boolean.parseBoolean(command[0]), Integer.parseInt(command[1]));
            }
        } catch (SQLException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        resp.sendRedirect("manager?part=tariff");
    }
}
