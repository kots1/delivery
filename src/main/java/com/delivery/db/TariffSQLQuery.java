package com.delivery.db;

public class TariffSQLQuery {
    private TariffSQLQuery() {
    }
    private static final String TABLE_NAME="tariff";
    public static final String FIELD_ID="id_tariff";
    public static final String FIELD_NAME="name";
    public static final String FIELD_PRICE_KM="price_per_km";
    public static final String FIELD_PRICE_KG="price_per_kg";
    public static final String FIELD_PRICE_M3="price_per_m3";
    public static final String FIELD_TRANSPORT="id_transport";

    public static final String  INSERT_TARIFF=
            "insert into "+TABLE_NAME+
                    "("+FIELD_NAME+" ,"
                    +FIELD_PRICE_KM+" ,"
                    +FIELD_PRICE_KG+" ,"
                    +FIELD_PRICE_M3+" ,"
                    +FIELD_TRANSPORT+
                    ") values(?,?,?,?,?)";

    public static final String  SELECT_TARIFF="select * from "+TABLE_NAME;

}
