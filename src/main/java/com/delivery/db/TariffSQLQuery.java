package com.delivery.db;

public class TariffSQLQuery {



    private TariffSQLQuery() {
    }
    public static final String FIELD_ID="id_tariff";
    public static final String FIELD_NAME="tariff_name";
    public static final String FIELD_PRICE_KM="price_per_km";
    public static final String FIELD_PRICE_KG="price_per_kg";
    public static final String FIELD_PRICE_M3="price_per_m3";
    public static final String FIELD_KG="max_weight";
    public static final String FIELD_M3="max_volume";
    public static final String FIELD_TIME="time_per_100km";
    public static final String FIELD_ALIVE="is_alive";

    public static final String  INSERT_TARIFF=
            "insert into tariff"+
                    "(price_per_km ,"+
                    "price_per_kg ,"+
                    "price_per_m3 ,"+
                    "max_weight ,"+
                    "max_volume ,"+
                    "time_per_100km"+
                    ") values(?,?,?,?,?,?)";

    public static final String INSERT_LOCALE_NAME = "insert into " +
            "tariff_name(id_locale,id_tariff,tariff_name) " +
            "values((select locale_id from locale where locale_name = ?),?,?)";

    public static final String  SELECT_TARIFF="with tmp(id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive) as(\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name=?) )\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name='en') \n" +
            "            and tariff.id_tariff not in (select id_tariff from tmp)" +
            "            limit ?,?";

    public static final String  SELECT_ALIVE_TARIFF="with tmp(id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive) as(\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name=?) " +
            "            and tariff.is_alive = 1)\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name='en') \n" +
            "            and tariff.id_tariff not in (select id_tariff from tmp)" +
            "            and tariff.is_alive = 1";

    public static final String SELECT_TARIFF_BY_ID = "with tmp(id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive) as(\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name=?) " +
            "            and tariff.id_tariff=?)\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name='en') \n" +
            "            and tariff.id_tariff not in (select id_tariff from tmp)" +
            "            and tariff.id_tariff=?";

    public static final String UPDATE_ALIVE = "update tariff set is_alive = ? where id_tariff = ?";
    public static final String DELETE_TARIFF = "delete from tariff where id_tariff = ?";
    public static final String DELETE_TARIFF_NAME = "delete from tariff_name where id_tariff = ?";
    public static final String GET_COUNT = "select count(*) as 'count' from tariff";
    public static final String SELECT_ALIVE_TARIFF_LIMIT = "with tmp(id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive) as(\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name=?) " +
            "            and tariff.is_alive = 1)\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name='en') \n" +
            "            and tariff.id_tariff not in (select id_tariff from tmp)" +
            "            and tariff.is_alive = 1" +
            "            limit ? , ?";
    public static final String SELECT_ALIVE_TARIFF_WHERE_VOLUME_WEIGHT = "with tmp(id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive) as(\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name=?) " +
            "            and tariff.is_alive = 1)\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT tariff.id_tariff,price_per_km,price_per_kg,price_per_m3,max_weight,max_volume,time_per_100km,tariff_name,is_alive\n" +
            "            FROM tariff join tariff_name \n" +
            "            on tariff.id_tariff=tariff_name.id_tariff\n" +
            "            where tariff_name.id_locale = (select locale_id from locale where locale_name='en') \n" +
            "            and tariff.id_tariff not in (select id_tariff from tmp)" +
            "            and tariff.is_alive = 1" +
            "            and tariff.max_weight > ?" +
            "            and tariff.max_volume > ?";



}
