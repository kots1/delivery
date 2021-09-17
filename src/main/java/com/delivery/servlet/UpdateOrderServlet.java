package com.delivery.servlet;

import com.delivery.db.OrderDao;
import com.delivery.entity.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] command= {""};
        Enumeration<String> names = req.getParameterNames();
        if (names.hasMoreElements()){
            command = names.nextElement().split("_");
        }
        switch (command[0]){
            case "status":
                String status = req.getParameter(command[0]+"_"+command[1]);
                if (status.equals("in the way")){
                    OrderDao.getInstance().updatedDate(Integer.parseInt(command[1]),"dispatch");
                }
                if (status.equals("delivered")){
                    OrderDao.getInstance().updatedDate(Integer.parseInt(command[1]),"receiving");
                }
                if (!OrderDao.getInstance().updateStatus(Integer.parseInt(command[1]),status)){
                    req.setAttribute("errorMessage","cannot update status");
                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
                }
                break;
            case "delete":
                if (!OrderDao.getInstance().deleteOrder(Integer.parseInt(command[1]))){
                    req.setAttribute("errorMessage","cannot delete order");
                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
                }
                break;
        }
        resp.sendRedirect(req.getParameter("link"));
    }

}
