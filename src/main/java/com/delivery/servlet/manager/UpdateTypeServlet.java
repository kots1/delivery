package com.delivery.servlet.manager;

import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateType")
public class UpdateTypeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!TypeBaggageDAO.getInstance().delete(Integer.parseInt(req.getParameter("delete")))) {
            req.setAttribute("errorMessage", "cannot delete direction");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        resp.sendRedirect("manager");
    }
}
