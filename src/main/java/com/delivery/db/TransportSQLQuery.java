package com.delivery.db;

public class TransportSQLQuery {

    private TransportSQLQuery() {
    }
    private static final String TABLE_NAME="transport";
    private static final String FIELD_ID="id_transport";
    private static final String FIELD_DESC="description";
    private static final String FIELD_KG="max_kg";
    private static final String FIELD_M3="max_m3";
    private static final String FIELD_TIME="time_per_100km";

    public static final String  INSERT_TRANSPORT=
            "insert into "+TABLE_NAME+
                    "("+FIELD_DESC+" ,"
                    +FIELD_KG+" ,"
                    +FIELD_M3+" ,"
                    +FIELD_TIME+
                    ") values(?,?,?,?)";

    public static final String  SELECT_TRANSPORT="select * from "+TABLE_NAME;
    public static final String SELECT_TRANSPORT_BY_ID = "select * from "+TABLE_NAME+" where "+FIELD_ID+" = ?";

}
