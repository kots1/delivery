package com.delivery.servlet;

import com.delivery.EmailSend;
import com.delivery.Resources;
import com.delivery.db.OrderDao;
import com.delivery.entity.Order;
import com.delivery.entity.User;
import com.delivery.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

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
                if (!OrderService.updateStatus(status,Integer.parseInt(command[1]), (User) req.getSession().getAttribute("user"))){
                    req.setAttribute("errorMessage", Resources.getValue("error.order.noStatus"));
                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
                }
                break;
            case "delete":
                if (!OrderDao.getInstance().deleteOrder(Integer.parseInt(command[1]))){
                    req.setAttribute("errorMessage",Resources.getValue("error.order.noDelete"));
                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
                }
                break;
        }
        resp.sendRedirect(req.getParameter("link"));
    }

}
