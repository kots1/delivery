package com.delivery.db;

public class OrderSQLQuery {



    private OrderSQLQuery() {
    }
    public static final String FIELD_ID="order_id";
    public static final String FIELD_TARIFF="id_tariff";
    public static final String FIELD_USER="user_id";
    public static final String FIELD_DIRECTION="id_direction";
    public static final String FIELD_PRICE="price";
    public static final String FIELD_DESC="description_of_baggage";
    public static final String FIELD_VOLUME="volume";
    public static final String FIELD_WEIGHT="weight";
    public static final String FIELD_ORDER_DATE="order_date";
    public static final String FIELD_DISPATCH="dispatch_date";
    public static final String FIELD_RECEIVING="receiving_date";
    public static final String FIELD_STATUS="order_status";
    public static final String FIELD_STREET="street";
    public static final String FIELD_HOUSE="house";
    public static final String FIELD_APARTMENT="apartment";
    public static final String FIELD_TYPE="id_type";

    public static final String  INSERT_ORDER=
            "insert into `order` (id_tariff ,user_id \n" +
                    ",id_direction ,price ,description_of_baggage ,volume ,weight ,street ,house ,apartment,id_type) values(?,?,?,?,?,?,?,?,?,?,?)";

    public static final String  SELECT_ORDER="select * from `order`";
    public static final String UPDATE_STATUS = "update `order` set order_status = ? where order_id = ?";
    public static final String DELETE_ORDER = "delete from `order` where order_id = ?";
    public static final String UPDATE_DISPATCH = "update `order` set dispatch_date = ? where order_id = ?";
    public static final String UPDATE_RECEIVING ="update `order` set receiving_date = ? where order_id = ?" ;
    public static final String SELECT_ORDER_BY_USER_ID = "select * from `order` where user_id = ?";



}
