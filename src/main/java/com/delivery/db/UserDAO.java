package com.delivery.db;

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

    public static UserDAO getInstance() {
        if (userDAO==null){
            userDAO=new UserDAO();
        }
        return userDAO;
    }
    public boolean insertUser(User user) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(SQLQuery.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSecondName());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
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
        User user=null;
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(SQLQuery.SELECT_USER_BY_LOGIN);
            statement.setString(1,login);
            set= statement.executeQuery();
            if (set.next()){
                int id = set.getInt("user_id");
                String password =set.getString("password");
                String first_name =set.getString("first_name");
                String second_name =set.getString("second_name");
                String email=set.getString("email");
                String role=set.getString("role");
                String phone=set.getString("phone");
                user= new User(id,login,first_name,second_name,password,email,phone,role);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally {
            dbManager.closeObject(connection,statement,set);
        }
        return user;
    }
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.createStatement();
            set= statement.executeQuery(SQLQuery.SELECT_USERS);
            while (set.next()){
                int id = set.getInt("user_id");
                String password =set.getString("password");
                String first_name =set.getString("first_name");
                String login =set.getString("login");
                String second_name =set.getString("second_name");
                String email=set.getString("email");
                String phone=set.getString("phone");
                String role=set.getString("role");
                users.add( new User(id,login,first_name,second_name,password,email,phone,role));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return users;
    }
}
