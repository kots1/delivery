package com.delivery.servlet.manager;

import com.delivery.db.DirectionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateDirection")
public class UpdateDirectionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("delete")!=null){
            if (!DirectionDAO.getInstance().delete(Integer.parseInt(req.getParameter("delete")))){
                req.setAttribute("errorMessage","cannot delete direction");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }
        }
        if (req.getParameter("alive")!=null){
            String[] command = req.getParameter("alive").split("_");
            if (!DirectionDAO.getInstance().updateIsAlive(Boolean.parseBoolean(command[0]),Integer.parseInt(command[1]))){
                req.setAttribute("errorMessage","cannot update status");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }
        }
        resp.sendRedirect("manager?part=direction");
    }
}
