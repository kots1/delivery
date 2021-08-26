package com.delivery;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Config implements ServletContextListener {

    private static String dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dataSource = sce.getServletContext().getInitParameter("DataSource");
    }

    public static String getDataSource() {
        return dataSource;
    }
}
