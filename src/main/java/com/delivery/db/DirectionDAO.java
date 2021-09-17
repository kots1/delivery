package com.delivery.db;

import com.delivery.CurrentLocale;
import com.delivery.entity.Direction;

import java.sql.*;
import java.util.List;

public class DirectionDAO {

    private static DirectionDAO directionDAO;
    private final DBManager dbManager;

    public DirectionDAO() {
        dbManager = DBManager.getInstance();
    }

    public synchronized static DirectionDAO getInstance() {
        if (directionDAO == null) {
            directionDAO = new DirectionDAO();
        }
        return directionDAO;
    }

    public boolean insertDirection(Direction direction) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(DirectionSQLQuery.INSERT_DIRECTION, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, direction.getDistance());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                direction.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, resultSet);
        }
        return false;
    }

    public boolean insertLocaleDirections(String locale, Direction direction, String startCity, String finalCity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(DirectionSQLQuery.INSERT_LOCALE_NAME);
            int index = 1;
            statement.setInt(index++, direction.getId());
            statement.setString(index++, locale);
            statement.setString(index++, startCity);
            statement.setString(index, finalCity);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement);
        }
        return false;
    }

    public List<Direction> getAllDirection() {
        return dbManager.getAllElements(new DirectionMapper(), DirectionSQLQuery.SELECT_DIRECTION);
    }

    public List<Direction> getAllAliveDirection() {
        return dbManager.getAllElements(new DirectionMapper(), DirectionSQLQuery.SELECT_ALIVE_DIRECTION);
    }

    public Direction getDirectionById(int id) {
        Direction direction = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(DirectionSQLQuery.SELECT_DIRECTION_BY_ID);
            int index = 1;
            statement.setString(index++, CurrentLocale.getLocale());
            statement.setInt(index++, id);
            statement.setInt(index, id);
            set = statement.executeQuery();
            if (set.next()) {
                direction = new DirectionMapper().mapRow(set);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, set);
        }
        return direction;
    }

    public boolean delete(int id) {
        return dbManager.deleteElement(id, DirectionSQLQuery.DELETE_DIRECTION_INFO)
                && dbManager.deleteElement(id, DirectionSQLQuery.DELETE_DIRECTION);
    }

    public boolean updateIsAlive(boolean status, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(DirectionSQLQuery.UPDATE_ALIVE);
            int index = 1;
            statement.setBoolean(index++, status);
            statement.setInt(index, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement);
        }
        return false;
    }

    public static class DirectionMapper implements EntityMapper<Direction> {

        @Override
        public Direction mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt(DirectionSQLQuery.FIELD_ID);
            int distance = rs.getInt(DirectionSQLQuery.FIELD_DISTANCE);
            String finalCity = rs.getString(DirectionSQLQuery.FIELD_FINAL);
            String startCity = rs.getString(DirectionSQLQuery.FIELD_START);
            boolean isAlive = rs.getBoolean(DirectionSQLQuery.FIELD_ALIVE);
            Direction direction = new Direction(id, distance, startCity, finalCity);
            direction.setAlive(isAlive);
            return direction;
        }
    }
}
