package com.delivery.FilterBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class OrderFilterBuilder {
    private static final String SELECT_DIRECTION_FILTER_START =
            "select * from `order`";

    private static final String LIMIT_ELEMENT =
            "   limit ? , ?";
    private static final String ORDER =
            "   order by order_id desc";

    public static String filterLimitsQuery(String orderDate, int[] directionId) {
        return filtering(orderDate, directionId) +ORDER+ LIMIT_ELEMENT;
    }

    public static String filterQuery(String orderDate, int[] directionId) {
        return filtering(orderDate, directionId)+ORDER;
    }

    private static String filtering(String orderDate, int[] directionId) {
        StringBuilder filter = new StringBuilder();
        if ((orderDate == null||orderDate.equals("")) && directionId == null) {
            return SELECT_DIRECTION_FILTER_START;
        }
        if (directionId != null) {
            filter.append("id_direction").append(" in (")
                    .append(Arrays.stream(directionId).mapToObj(String::valueOf)
                            .collect(Collectors.joining(", ")))
                    .append(")");
        }
        if (orderDate != null && !orderDate.equals("")) {
            if (filter.length() != 0) {
                filter.append(" and ");
            }
            filter.append("Date(order_date) = '").append(orderDate).append("'");

        }
        System.out.println(filter);
        return SELECT_DIRECTION_FILTER_START + " where " + filter;
    }

}
