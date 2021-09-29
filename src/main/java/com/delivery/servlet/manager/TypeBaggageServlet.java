package com.delivery.servlet.manager;

import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.TypeBaggage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/typeBaggage")
public class TypeBaggageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       double coefficient = Double.parseDouble(req.getParameter("coefficient"));
       String type = req.getParameter("type");
        TypeBaggage typeBaggage = new TypeBaggage(type,coefficient);
        if (!TypeBaggageDAO.getInstance().insertType(typeBaggage)) {
            req.setAttribute("errorMessage","cannot insert type");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
        resp.sendRedirect("manager?part=type");
    }
}
