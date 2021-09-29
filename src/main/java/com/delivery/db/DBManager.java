package com.delivery.db;


import com.delivery.CurrentLocale;
import com.delivery.listener.ConfigListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class DBManager {

    private static DBManager dbManager;
    private  DataSource dataSource;

    private DBManager() {
        try {
            Context  context = (Context) new InitialContext().lookup("java:/comp/env");
            this.dataSource = (DataSource) context.lookup(ConfigListener.getDataSourceName());

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public synchronized static DBManager getInstance() {
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

    public<E> List<E> getAllElementsUsingLocale(EntityMapper<E> mapper, String query){
        List<E> result = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, CurrentLocale.getLocale());
            set= statement.executeQuery();
            while (set.next()){
                result.add( mapper.mapRow(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeObject(connection,statement,set);
        }
        return result;
    }
    public<E> List<E> getAllElements(EntityMapper<E> mapper, String query){
        List<E> result = new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet set=null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            set= statement.executeQuery(query);
            while (set.next()){
                result.add( mapper.mapRow(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeObject(connection,statement,set);
        }
        return result;
    }

    public<E> List<E> getAllElementsWithLimitUsingLocale(EntityMapper<E> mapper, String query,int start,int count){
        List<E> result = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            int index= 1;
            statement.setString(index++, CurrentLocale.getLocale());
            statement.setInt(index++, start-1);
            statement.setInt(index, count);
            set= statement.executeQuery();
            while (set.next()){
                result.add( mapper.mapRow(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeObject(connection,statement,set);
        }
        return result;
    }

    public<E> List<E> getAllElementsWithLimit(EntityMapper<E> mapper, String query,int start,int count){
        List<E> result = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            int index= 1;
            statement.setInt(index++, start-1);
            statement.setInt(index, count);
            set= statement.executeQuery();
            while (set.next()){
                result.add( mapper.mapRow(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeObject(connection,statement,set);
        }
        return result;
    }

    public boolean deleteElement(int id,String query){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeObject(connection, statement);
        }
        return false;
    }

    public int getCount(String query){
        Connection connection = null;
        Statement statement = null;
        ResultSet set=null;
        int res = 0;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            set= statement.executeQuery(query);
            if (set.next()){
                res = set.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeObject(connection, statement,set);
        }
        return res;
    }
}
