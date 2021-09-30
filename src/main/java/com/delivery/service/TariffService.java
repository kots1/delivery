package com.delivery.service;

import com.delivery.db.DBManager;
import com.delivery.db.TariffDAO;
import com.delivery.entity.Tariff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class TariffService {

    public static void insertTariff(Tariff tariff, Map<String, String> localesInfo) throws SQLException {
        Connection connection = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            TariffDAO tariffDAO = TariffDAO.getInstance();
            tariffDAO.insertTariff(connection, tariff);
            for (Map.Entry<String, String> locale : localesInfo.entrySet()) {
                tariffDAO.insertLocaleTariffName(connection, locale.getKey(), locale.getValue(), tariff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            dbManager.rollbackAndClose(connection);
            throw new SQLException(e.getMessage());
        }
        dbManager.commitAndClose(connection);
    }

    public static void delete(int id) throws SQLException {
        Connection connection = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            TariffDAO tariffDAO = TariffDAO.getInstance();
            tariffDAO.delete(id,connection);
        } catch (SQLException e) {
            e.printStackTrace();
            dbManager.rollbackAndClose(connection);
            throw new SQLException(e.getMessage());
        }
        dbManager.commitAndClose(connection);
    }
}
