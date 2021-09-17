package com.delivery.db;

public  class UserSQLQuery {

    private UserSQLQuery() {
    }
    public static final String FIELD_ID="user_id";
    public static final String FIELD_LOGIN="login";
    public static final String FIELD_NAME="first_name";
    public static final String FIELD_SECOND_NAME="second_name";
    public static final String FIELD_PHONE="phone";
    public static final String FIELD_EMAIL="email";
    public static final String FIELD_ROLE="role_id";
    public static final String FIELD_PASSWORD="password";

    public static final String  INSERT_USER=
                             "insert into user"+
                            "( login ,"
                            +"password ,"
                            +"first_name ,"
                            +"second_name ,"
                            +"email,"
                            +"phone"+
                            ") values(?,?,?,?,?,?)";

    public static final String  SELECT_USERS="select * from user";
    public static final String SELECT_USER_BY_LOGIN ="select * from user where login = ?";

    public static final String SELECT_USER_BY_ID = "select * from user where user_id = ?";

}
