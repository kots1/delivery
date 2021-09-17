package com.delivery.db;

import com.delivery.entity.Tariff;
import com.delivery.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO userDAO;
    private final DBManager dbManager;

    private UserDAO() {
        dbManager = DBManager.getInstance();
    }


    public synchronized static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public boolean insertUser(User user) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(UserSQLQuery.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            statement.setString(index++, user.getLogin());
            statement.setString(index++, user.getPassword());
            statement.setString(index++, user.getName());
            statement.setString(index++, user.getSecondName());
            statement.setString(index++, user.getEmail());
            statement.setString(index, user.getPhone());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, resultSet);
        }
        return false;
    }

    public User getUserByLogin(String login) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(UserSQLQuery.SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            set = statement.executeQuery();
            if (set.next()) {
                user = new UserMapper().mapRow(set);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, set);
        }
        return user;
    }

    public List<User> findAllUsers() {
        return dbManager.getAllElements(new  UserMapper(),UserSQLQuery.SELECT_USERS);
    }

    public User getUserById(int userId) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(UserSQLQuery.SELECT_USER_BY_ID);
            statement.setInt(1, userId);
            set = statement.executeQuery();
            if (set.next()) {
                user= new UserMapper().mapRow(set);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, set);
        }
        return user;
    }
    private static class UserMapper implements EntityMapper<User>{

        @Override
        public User mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt(UserSQLQuery.FIELD_ID);
            String password = rs.getString(UserSQLQuery.FIELD_PASSWORD);
            String first_name = rs.getString(UserSQLQuery.FIELD_NAME);
            String login = rs.getString(UserSQLQuery.FIELD_LOGIN);
            String second_name = rs.getString(UserSQLQuery.FIELD_SECOND_NAME);
            String email = rs.getString(UserSQLQuery.FIELD_EMAIL);
            String phone = rs.getString(UserSQLQuery.FIELD_PHONE);
            int roleId = rs.getInt(UserSQLQuery.FIELD_ROLE);
            return new User(id, login, first_name, second_name, password, email, phone, roleId);
        }
    }
}
