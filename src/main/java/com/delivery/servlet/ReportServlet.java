package com.delivery.servlet;

import com.delivery.PDFManager;
import com.delivery.db.OrderDao;
import com.delivery.entity.Order;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ServletContext context = req.getSession().getServletContext();

        String path = context.getRealPath("report.pdf");
        PDFManager.readFile(path,out);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getSession().getServletContext();

        String path = context.getRealPath("report.pdf");
        String chosenDate=req.getParameter("orderDate");
        int[] directionId=null;
        if (req.getParameterValues("directionId")!=null) {
            directionId = Stream.of(req.getParameterValues("directionId"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        List<Order> orderList = OrderDao.getInstance().getAllOrderWithFilter(chosenDate,directionId);
        PDFManager.generatePDF(path,orderList);
        resp.sendRedirect(req.getContextPath()+"/report");
    }
}

