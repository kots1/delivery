package com.delivery.db;

public class SQLQuery {
    private SQLQuery() {
    }


    public static final String  INSERT_USER="insert into user(login ,password ,first_name ,second_name ,email,phone) values(?,?,?,?,?,?)";

    public static final String  SELECT_USERS="select * from user";
    public static final String SELECT_USER_BY_LOGIN ="select * from user where login = ?";


}
