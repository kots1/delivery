package com.delivery.servlet.manager;

import com.delivery.db.DirectionDAO;
import com.delivery.service.DirectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateDirection")
public class UpdateDirectionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("delete") != null) {
                DirectionService.delete(Integer.parseInt(req.getParameter("delete")));
            }
            if (req.getParameter("alive") != null) {
                String[] command = req.getParameter("alive").split("_");
                DirectionDAO.getInstance().updateIsAlive(Boolean.parseBoolean(command[0]), Integer.parseInt(command[1]));
            }
        } catch (SQLException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        resp.sendRedirect("manager?part=direction");
    }
}
