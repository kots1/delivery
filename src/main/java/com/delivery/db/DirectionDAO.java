package com.delivery.db;

import com.delivery.CurrentLocale;
import com.delivery.FilterBuilder.DirectionFilterBuilder;
import com.delivery.entity.Direction;

import java.sql.*;
import java.util.ArrayList;
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

    public void insertDirection(Connection connection, Direction direction) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(DirectionSQLQuery.INSERT_DIRECTION, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, direction.getDistance());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                direction.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Cannot insert Direction", e);

        } finally {
            dbManager.closeObject(statement, resultSet);
        }
    }

    public void insertLocaleDirections(Connection connection, String locale, Direction direction, String startCity, String finalCity) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DirectionSQLQuery.INSERT_LOCALE_NAME);
            int index = 1;
            statement.setInt(index++, direction.getId());
            statement.setString(index++, locale);
            statement.setString(index++, startCity);
            statement.setString(index, finalCity);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Cannot insert locale " + locale + " in direction", e);
        } finally {
            dbManager.closeObject(statement);
        }
    }

    public List<Direction> getAllDirectionWithLimit(int start, int count) {
        return dbManager.getAllElementsWithLimitUsingLocale(new DirectionMapper(), DirectionSQLQuery.SELECT_DIRECTION_WITH_LIMIT, start, count);
    }

    public List<Direction> getAllDirection() {
        return dbManager.getAllElementsUsingLocale(new DirectionMapper(), DirectionSQLQuery.SELECT_DIRECTION);
    }

    public List<Direction> getAllAliveDirection() {
        return dbManager.getAllElementsUsingLocale(new DirectionMapper(), DirectionSQLQuery.SELECT_ALIVE_DIRECTION);
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

    public List<Direction> getDirectionFilterCities(String[] finalCity, String[] startCity, int start, int count) {
        return dbManager.getAllElementsWithLimitUsingLocale(new DirectionMapper(), DirectionFilterBuilder.filterLimitsQuery(finalCity, startCity), start, count);
    }

    public int getDirectionFilterCitiesCount(String[] finalCity, String[] startCity) {
        return dbManager
                .getAllElementsUsingLocale(new DirectionMapper(), DirectionFilterBuilder.filterQuery(finalCity, startCity))
                .size();
    }

    public List<String> getDistinctDirectionFinalCity() {
        return getDistinctDirectionCity(DirectionSQLQuery.SELECT_DIRECTION_DISTINCT_FINAL_CITY, DirectionSQLQuery.FIELD_FINAL);
    }

    private List<String> getDistinctDirectionCity(String query, String fieldName) {
        List<String> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, CurrentLocale.getLocale());
            set = statement.executeQuery();
            while (set.next()) {
                result.add(set.getString(fieldName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.closeObject(connection, statement, set);
        }
        return result;
    }

    public void delete(int id, Connection connection) throws SQLException {
        dbManager.deleteElement(id, DirectionSQLQuery.DELETE_DIRECTION_INFO, connection);
        dbManager.deleteElement(id, DirectionSQLQuery.DELETE_DIRECTION, connection);

    }

    public void updateIsAlive(boolean status, int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(DirectionSQLQuery.UPDATE_ALIVE);
            int index = 1;
            statement.setBoolean(index++, status);
            statement.setInt(index, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("cannot update direction status"+status,e);
        } finally {
            dbManager.closeObject(connection, statement);
        }
    }

    public List<String> getDistinctDirectionStartCity() {
        return getDistinctDirectionCity(DirectionSQLQuery.SELECT_DIRECTION_DISTINCT_START_CITY, DirectionSQLQuery.FIELD_START);

    }

    public int getCount() {
        return dbManager.getCount(DirectionSQLQuery.GET_COUNT);
    }

    public int getAliveCount() {
        return dbManager.getCount(DirectionSQLQuery.GET_ALIVE_COUNT);
    }

    public static class DirectionMapper implements EntityMapper<Direction> {

        @Override
        public Direction mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt(DirectionSQLQuery.FIELD_ID);
            int distance = rs.getInt(DirectionSQLQuery.FIELD_DISTANCE);
            String finalCity = rs.getString(DirectionSQLQuery.FIELD_FINAL);
            String startCity = rs.getString(DirectionSQLQuery.FIELD_START);
            boolean isAlive = rs.getBoolean(DirectionSQLQuery.FIELD_ALIVE);
            Direction direction = new Direction(id, distance, finalCity, startCity);
            direction.setAlive(isAlive);
            return direction;
        }
    }
}
