package com.delivery.db;

import com.delivery.entity.Transport;
import com.delivery.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportDAO {

    private static TransportDAO transportDAO;
    private final DBManager dbManager;

    private TransportDAO() {
        dbManager = DBManager.getInstance();
    }

    public static TransportDAO getInstance() {
        if (transportDAO==null){
            transportDAO=new TransportDAO();
        }
        return transportDAO;
    }

    public  List<Transport> getAllTransport() {
        List<Transport> transports = new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.createStatement();
            set= statement.executeQuery(TransportSQLQuery.SELECT_TRANSPORT);
            while (set.next()){
                int id = set.getInt("id_transport");
                String description =set.getString("description");
                int maxKg =set.getInt("max_kg");
                int maxM3 =set.getInt("max_m3");
                int timePer100km =set.getInt("time_per_100km");
                transports.add( new Transport(id,description,maxKg,maxM3,timePer100km));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return transports;
    }
    public  boolean insertTransport(Transport transport) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TransportSQLQuery.INSERT_TRANSPORT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, transport.getDescription());
            statement.setInt(2, transport.getMaxKg());
            statement.setInt(3, transport.getMaxM3());
            statement.setInt(4, transport.getTimePer100km());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                transport.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, resultSet);
        }
        return false;
    }
    public  Transport getTransportById(int id) {
        Transport transport =null;
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TransportSQLQuery.SELECT_TRANSPORT_BY_ID);
            statement.setInt(1,id);
            set= statement.executeQuery();
            if (set.next()){
                String description =set.getString("description");
                int maxKg =set.getInt("max_kg");
                int maxM3 =set.getInt("max_m3");
                int timePer100km =set.getInt("time_per_100km");
                transport = new Transport(id,description,maxKg,maxM3,timePer100km);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return transport;
    }
}
