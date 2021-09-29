package com.delivery.db;

public class DirectionSQLQuery {


    public static final String FIELD_ID = "id_direction";
    public static final String FIELD_DISTANCE = "distance";
    public static final String FIELD_FINAL = "final_city";
    public static final String FIELD_START = "start_city";
    public static final String FIELD_ALIVE = "is_alive";
    public static final String INSERT_DIRECTION =
            "insert into direction" +
                    "(distance) values(?)";
    public static final String INSERT_LOCALE_NAME = "insert into " +
            "direction_information(direction_id,locale_id,final_city,start_city) " +
            "values(?,(select locale_id from locale where locale_name = ?),?,?)";
    public static final String SELECT_DIRECTION_WITH_LIMIT = "with tmp(id_direction,distance,final_city,start_city,is_alive) as(\n" +
            "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "            FROM direction join direction_information \n" +
            "            on direction.id_direction=direction_information.direction_id\n" +
            "            where direction_information.locale_id = (select locale_id from locale where locale_name=?) )\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "            FROM direction join direction_information \n" +
            "            on direction.id_direction=direction_information.direction_id\n" +
            "            where direction_information.locale_id = (select locale_id from locale where locale_name='en')\n" +
            "            and direction.id_direction not in (select id_direction from tmp)" +
            "            limit ? , ?";
    public static final String SELECT_DIRECTION = "with tmp(id_direction,distance,final_city,start_city,is_alive) as(\n" +
            "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "            FROM direction join direction_information \n" +
            "            on direction.id_direction=direction_information.direction_id\n" +
            "            where direction_information.locale_id = (select locale_id from locale where locale_name=?) )\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "            FROM direction join direction_information \n" +
            "            on direction.id_direction=direction_information.direction_id\n" +
            "            where direction_information.locale_id = (select locale_id from locale where locale_name='en')\n" +
            "            and direction.id_direction not in (select id_direction from tmp)";
    public static final String SELECT_DIRECTION_DISTINCT_FINAL_CITY = "with tmp(final_city,id_direction)\n" +
            "             as(SELECT final_city ,direction.id_direction \n" +
            "            FROM direction_information join direction \n" +
            "             on direction.id_direction=direction_information.direction_id\n" +
            "             where locale_id = (select locale_id from locale where locale_name = ?)\n" +
            "             and direction.is_alive = 1)\n" +
            "            select * from tmp\n" +
            "             group by final_city\n" +
            "            union \n" +
            "             SELECT final_city ,direction_information.direction_id\n" +
            "            FROM direction_information join direction \n" +
            "            on direction.id_direction=direction_information.direction_id" +
            "             where direction_information.locale_id = (select locale_id from locale where locale_name='en') \n" +
            "             and direction_information.direction_id not in (select id_direction from tmp) \n" +
            "             and direction.is_alive = 1\n" +
            "            group by final_city";
    public static final String SELECT_ALIVE_DIRECTION = "with tmp(id_direction,distance,final_city,start_city,is_alive) as(\n" +
            "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "            FROM direction join direction_information \n" +
            "            on direction.id_direction=direction_information.direction_id\n" +
            "            where direction_information.locale_id = (select locale_id from locale where locale_name=?) " +
            "            and direction.is_alive = 1)\n" +
            "            select * from tmp\n" +
            "            union\n" +
            "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "            FROM direction join direction_information \n" +
            "            on direction.id_direction=direction_information.direction_id\n" +
            "            where direction_information.locale_id = (select locale_id from locale where locale_name='en') \n" +
            "            and direction.id_direction not in (select id_direction from tmp)" +
            "            and direction.is_alive = 1";
    public static final String SELECT_DIRECTION_BY_ID = "with tmp(id_direction,distance,final_city,start_city,is_alive) as(\n" +
            "                        SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "                       FROM direction join direction_information \n" +
            "                        on direction.id_direction=direction_information.direction_id\n" +
            "                        where direction_information.locale_id = (select locale_id from locale where locale_name=?) \n" +
            "                        and direction.id_direction=?)\n" +
            "                        select * from tmp\n" +
            "                        union\n" +
            "                        SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
            "                        FROM direction join direction_information \n" +
            "                        on direction.id_direction=direction_information.direction_id\n" +
            "                        where direction_information.locale_id = (select locale_id from locale where locale_name='en')\n" +
            "                        and direction.id_direction not in (select id_direction from tmp)\n" +
            "                        and direction.id_direction=?";
    public static final String UPDATE_ALIVE = "update direction set is_alive = ? where id_direction = ?";
    public static final String DELETE_DIRECTION_INFO = "delete from direction_information where direction_id = ?";
    public static final String DELETE_DIRECTION = "delete from direction where id_direction = ?";
    public static final String SELECT_DIRECTION_DISTINCT_START_CITY = "with tmp(start_city,id_direction)\n" +
            "             as(SELECT start_city ,direction.id_direction \n" +
            "            FROM direction_information join direction \n" +
            "             on direction.id_direction=direction_information.direction_id\n" +
            "             where locale_id = (select locale_id from locale where locale_name = ?)\n" +
            "             and direction.is_alive = 1)\n" +
            "            select * from tmp\n" +
            "             group by start_city\n" +
            "            union \n" +
            "             SELECT start_city ,direction_information.direction_id\n" +
            "            FROM direction_information join direction \n" +
            "            on direction.id_direction=direction_information.direction_id" +
            "             where direction_information.locale_id = (select locale_id from locale where locale_name='en') \n" +
            "             and direction_information.direction_id not in (select id_direction from tmp) \n" +
            "             and direction.is_alive = 1\n" +
            "            group by start_city";

    public static final String GET_ALIVE_COUNT = "select count(*) as 'count' from direction where direction.is_alive = 1";
    public static final String GET_COUNT = "select count(*) as 'count' from direction";
    private static final String TABLE_NAME = "direction";
    private DirectionSQLQuery() {
    }


}
