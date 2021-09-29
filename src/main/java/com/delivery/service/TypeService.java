package com.delivery.service;

import com.delivery.db.DBManager;
import com.delivery.db.TypeBaggageDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TypeService {
    public static void delete(int id) throws SQLException {
        Connection connection = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            TypeBaggageDAO typeDAO = TypeBaggageDAO.getInstance();
            typeDAO.delete(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            dbManager.rollbackAndClose(connection);
            throw new SQLException("cannot delete type", e);
        }
        dbManager.commitAndClose(connection);
    }
}
