package com.delivery.entity;

import java.io.Serializable;

public class Tariff implements Serializable {
    private int id;
    private String name;
    private int pricePerKm;
    private int pricePerM3;
    private int pricePerKg;
    private Transport transport;

    public Tariff(int id, String name, int pricePerKm, int pricePerM3, int pricePerKg, Transport transport) {
        this.id = id;
        this.name = name;
        this.pricePerKm = pricePerKm;
        this.pricePerM3 = pricePerM3;
        this.pricePerKg = pricePerKg;
        this.transport = transport;
    }

    public static Tariff createTariff(String name, int pricePerKm, int pricePerM3, int pricePerKg, Transport transport) {
        return new Tariff(0,name,pricePerKm,pricePerM3,pricePerKg,transport);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(int pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public int getPricePerM3() {
        return pricePerM3;
    }

    public void setPricePerM3(int pricePerM3) {
        this.pricePerM3 = pricePerM3;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(int pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
