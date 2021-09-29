package com.delivery.Auth;

import com.delivery.entity.User;
import com.delivery.db.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;


public class Authentication {


    public static User register(String login, String name, String secondName, String password, String email,String phone) throws SQLException {

        String generatedSecuredPasswordHash = PasswordHashing.generatePasswordHash(password);
        User user = User.createUser(login,name,secondName,generatedSecuredPasswordHash,email,phone);
        if (UserDAO.getInstance().isAlreadyExist(login)){
            throw new SQLException("user with this login already exist");
        }
         if (!UserDAO.getInstance().insertUser(user)){
         throw new SQLException();
         }
        return user;
    }

    public static User login(String login, String password) throws IllegalPasswordException{
        User user = UserDAO.getInstance().getUserByLogin(login);
        if (user==null){
            return null;
        }
        try {
            if (!PasswordHashing.validatePassword(password,user.getPassword())){
                throw new IllegalPasswordException();
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return user;
    }
}
