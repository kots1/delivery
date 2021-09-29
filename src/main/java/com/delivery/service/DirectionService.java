package com.delivery.service;

import com.delivery.db.DBManager;
import com.delivery.db.DirectionDAO;
import com.delivery.entity.Direction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DirectionService {

    public static void insertDirection(Direction direction, Map<String, String[]> localesInfo) throws SQLException {
        Connection connection = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            DirectionDAO directionDAO = DirectionDAO.getInstance();
            directionDAO.insertDirection(connection, direction);
            for (Map.Entry<String, String[]> locale : localesInfo.entrySet()) {
                directionDAO.insertLocaleDirections(connection, locale.getKey(), direction, locale.getValue()[0], locale.getValue()[1]);
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
            DirectionDAO directionDAO = DirectionDAO.getInstance();
            directionDAO.delete(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            dbManager.rollbackAndClose(connection);
            throw new SQLException(e.getMessage());
        }
        dbManager.commitAndClose(connection);
    }
}
