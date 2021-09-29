package com.delivery.servlet.manager;

import com.delivery.db.TypeBaggageDAO;
import com.delivery.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateType")
public class UpdateTypeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete") != null) {
            try {
              TypeService.delete(Integer.parseInt(req.getParameter("delete")));
            } catch (SQLException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect("manager?part=type");
    }
}
