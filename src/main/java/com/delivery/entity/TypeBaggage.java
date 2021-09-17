package com.delivery.entity;

public class TypeBaggage {
    private int id;
    private String type;
    private double coefficient;

    public TypeBaggage(int id, String type, double coefficient) {
        this.id = id;
        this.type = type;
        this.coefficient = coefficient;
    }

    public TypeBaggage(String type, double coefficient) {
        this.type = type;
        this.coefficient = coefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
