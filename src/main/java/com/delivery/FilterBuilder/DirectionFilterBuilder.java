package com.delivery.FilterBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DirectionFilterBuilder {
    private static final String SELECT_DIRECTION_FILTER_START =
            "with tmp(id_direction,distance,final_city,start_city,is_alive) as(\n" +
                    "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
                    "            FROM direction join direction_information \n" +
                    "            on direction.id_direction=direction_information.direction_id\n" +
                    "            where direction_information.locale_id = (select locale_id from locale where locale_name=?) " +
                    "            and direction.is_alive = 1)" +
                    "            select * from tmp\n";
    private static final String SELECT_DIRECTION_FILTER_END =
            "            union\n" +
                    "            SELECT direction.id_direction,distance,final_city,start_city,is_alive\n" +
                    "            FROM direction join direction_information \n" +
                    "            on direction.id_direction=direction_information.direction_id\n" +
                    "            where direction_information.locale_id = (select locale_id from locale where locale_name='en')\n" +
                    "            and direction.id_direction not in (select id_direction from tmp)" +
                    "    and direction.is_alive = 1";
    private static final String LIMIT_ELEMENT =
            "    limit ? , ?";

    public static String filterLimitsQuery(String[] finalCity, String[] startCity) {
        return filtering(finalCity, startCity) + LIMIT_ELEMENT;
    }

    public static String filterQuery(String[] finalCity, String[] startCity) {
        return filtering(finalCity, startCity);
    }

    private static String filtering(String[] finalCity, String[] startCity) {
        StringBuilder filter = new StringBuilder();
        if (finalCity == null && startCity == null) {
            return SELECT_DIRECTION_FILTER_START + SELECT_DIRECTION_FILTER_END;
        }
        if (finalCity != null) {
            filter.append("final_city in ( ").append(Arrays.stream(finalCity)
                    .map(s -> "'" + s + "'")
                    .collect(Collectors.joining(", "))).append(")");
        }
        if (startCity != null) {
            if (filter.length() != 0) {
                filter.append(" and ");
            }
            filter.append("start_city in ( ").append(Arrays.stream(startCity)
                    .map(s -> "'" + s + "'")
                    .collect(Collectors.joining(", "))).append(")");
        }
        return SELECT_DIRECTION_FILTER_START + " where " + filter
                + SELECT_DIRECTION_FILTER_END + " and " + filter;
    }

}
