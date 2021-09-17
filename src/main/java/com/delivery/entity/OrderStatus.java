package com.delivery.entity;

import java.util.ArrayList;
import java.util.List;


public enum OrderStatus {
    UNPAID, PROCESSING, IN_THE_WAY, DELIVERED, TAKEN;

    public static List<String> getArray(){
        List<String> value = new ArrayList<>();
        for (OrderStatus status:values()){
            value.add(status.name().toLowerCase().replace("_"," "));
        }
        return value;
    }
}
