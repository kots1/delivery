package com.delivery.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ServletContext context = req.getSession().getServletContext();

        String path = context.getRealPath("report.pdf");
        FileInputStream fileInputStream = new FileInputStream(path);
        int i;
        while( (i = fileInputStream.read()) != -1 )
        {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}

