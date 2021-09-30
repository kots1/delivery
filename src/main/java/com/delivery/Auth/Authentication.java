package com.delivery.Auth;

import com.delivery.Resources;
import com.delivery.entity.User;
import com.delivery.db.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;


public class Authentication {


    public static User register(String login, String name, String secondName, String password, String email,String phone) throws SQLException, NoUniqueLoginException, NoUniqueEmailException {

        String generatedSecuredPasswordHash = PasswordHashing.generatePasswordHash(password);
        User user = User.createUser(login,name,secondName,generatedSecuredPasswordHash,email,phone);
        if (UserDAO.getInstance().isLoginExist(login)){
            throw new NoUniqueLoginException(Resources.getValue("login.loginExist"));
        }
        if (UserDAO.getInstance().isEmailExist(email)){
            throw new NoUniqueEmailException(Resources.getValue("login.emailExist"));
        }
         if (!UserDAO.getInstance().insertUser(user)){
         throw new SQLException(Resources.getValue("error.noInsertUser"));
         }
        return user;
    }

    public static User login(String login, String password) throws IllegalPasswordException {
        User user = UserDAO.getInstance().getUserByLogin(login);
        if (user==null){
            return null;
        }
        try {
            if (!PasswordHashing.validatePassword(password,user.getPassword())){
                throw new IllegalPasswordException(Resources.getValue("error.noPassword"));
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return user;
    }
}
