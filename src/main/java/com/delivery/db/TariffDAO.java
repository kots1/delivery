package com.delivery.db;

import com.delivery.entity.Tariff;
import com.delivery.entity.Transport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO {

    private static TariffDAO tariffDAO;
    private final DBManager dbManager;

    private TariffDAO() {
        dbManager = DBManager.getInstance();
    }

    public static TariffDAO getInstance() {
        if (tariffDAO==null){
            tariffDAO=new TariffDAO();
        }
        return tariffDAO;
    }

    public List<Tariff> getAllTariff() {
        List<Tariff> tariffs = new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.createStatement();
            set= statement.executeQuery(TariffSQLQuery.SELECT_TARIFF);
            while (set.next()){
                int id = set.getInt(TariffSQLQuery.FIELD_ID);
                String name =set.getString(TariffSQLQuery.FIELD_NAME);
                int priceKg =set.getInt(TariffSQLQuery.FIELD_PRICE_KG);
                int priceM3 =set.getInt(TariffSQLQuery.FIELD_PRICE_M3);
                int priceKm =set.getInt(TariffSQLQuery.FIELD_PRICE_KM);
                int idTransport =set.getInt(TariffSQLQuery.FIELD_TRANSPORT);
                Transport transport= TransportDAO.getInstance().getTransportById(idTransport);
                tariffs.add( new Tariff(id,name,priceKm,priceM3,priceKg,transport));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return tariffs;
    }

    public  boolean insertTariff(Tariff tariff) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TariffSQLQuery.INSERT_TARIFF, Statement.RETURN_GENERATED_KEYS);
            int index=1;
            statement.setString(index++, tariff.getName());
            statement.setInt(index++, tariff.getPricePerKm());
            statement.setInt(index++, tariff.getPricePerKg());
            statement.setInt(index++, tariff.getPricePerM3());
            statement.setInt(index, tariff.getTransport().getId());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                tariff.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, resultSet);
        }
        return false;
    }
}
