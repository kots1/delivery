package com.delivery.db;


import com.delivery.Config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class DBManager {

    private static DBManager dbManager;
    private  DataSource dataSource;

    private DBManager() {
        try {
            Context  context = (Context) new InitialContext().lookup("java:/comp/env");
            this.dataSource = (DataSource) context.lookup(Config.getDataSource());

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance() {
        if (dbManager==null){
            dbManager=new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public void closeObject(AutoCloseable... array){
        for (AutoCloseable obj:array){
            try {
                if (obj!=null){
                obj.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
