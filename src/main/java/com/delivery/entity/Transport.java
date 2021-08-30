package com.delivery.entity;

import java.io.Serializable;

public class Transport implements Serializable {
    private int id;
    private String description;
    private int maxKg;
    private int maxM3;
    private int timePer100km;

    public Transport(int id, String description, int maxKg, int maxM3, int timePer100km) {
        this.id = id;
        this.description = description;
        this.maxKg = maxKg;
        this.maxM3 = maxM3;
        this.timePer100km = timePer100km;
    }

    public static Transport createTransport(String description, int maxKg, int maxM3, int timePer100km) {
        return new Transport(0,description,maxKg,maxM3,timePer100km);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxKg() {
        return maxKg;
    }

    public void setMaxKg(int maxKg) {
        this.maxKg = maxKg;
    }

    public int getMaxM3() {
        return maxM3;
    }

    public void setMaxM3(int maxM3) {
        this.maxM3 = maxM3;
    }

    public int getTimePer100km() {
        return timePer100km;
    }

    public void setTimePer100km(int timePer100km) {
        this.timePer100km = timePer100km;
    }
}
