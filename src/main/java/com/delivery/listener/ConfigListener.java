package com.delivery.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ConfigListener implements ServletContextListener {

    private static String dataSourceName;
    private static List<String> locales;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dataSourceName = sce.getServletContext().getInitParameter("DataSource");
        initI18N(sce.getServletContext());
    }

    public static String getDataSourceName() {
        return dataSourceName;
    }

    public static List<String> getLocales() {
        return locales;
    }

    private void initI18N(ServletContext servletContext) {

        String localesValue = servletContext.getInitParameter("locales");
            List<String> locale = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(localesValue);
            while (st.hasMoreTokens()) {
                String localeName = st.nextToken();
                locale.add(localeName);
            }
            locales=locale;
            servletContext.setAttribute("locales", locale);
    }


}
