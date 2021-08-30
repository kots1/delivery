package com.delivery.db;

public class UserSQLQuery {
    private UserSQLQuery() {
    }
    private static final String TABLE_NAME="user";
    private static final String FIELD_ID="user_id";
    private static final String FIELD_LOGIN="login";
    private static final String FIELD_NAME="first_name";
    private static final String FIELD_SECOND_NAME="second_name";
    private static final String FIELD_PHONE="phone";
    private static final String FIELD_EMAIL="email";
    private static final String FIELD_ROLE="role";
    private static final String FIELD_PASSWORD="password";

    public static final String  INSERT_USER=
                             "insert into "+TABLE_NAME+
                            "("+FIELD_LOGIN+" ,"
                            +FIELD_PASSWORD+" ,"
                            +FIELD_NAME+" ,"
                            +FIELD_SECOND_NAME+" ,"
                            +FIELD_EMAIL+","
                            +FIELD_PHONE+
                            ") values(?,?,?,?,?,?)";

    public static final String  SELECT_USERS="select * from "+TABLE_NAME;
    public static final String SELECT_USER_BY_LOGIN ="select * from "+TABLE_NAME+" where "+FIELD_LOGIN+" = ?";


}
