package com.delivery.entity;

import java.io.Serializable;

public class Tariff implements Serializable {
    private int id;
    private String name;
    private double pricePerKm;
    private double pricePerM3;
    private double pricePerKg;
    private double maxWeight;
    private double maxVolume;
    private double timePer100km;
    private boolean  isAlive;


    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public double getTimePer100km() {
        return timePer100km;
    }

    public void setTimePer100km(double timePer100km) {
        this.timePer100km = timePer100km;
    }

    public static class Builder {
        private final Tariff tariff;

        public Builder() {
            tariff = new Tariff();
        }

        public Builder id(int id){
            tariff.id=id;
            return this;
        }

        public Builder name(String name){
            tariff.name=name;
            return this;
        }

        public Builder pricePerKm(double pricePerKm){
            tariff.pricePerKm=pricePerKm;
            return this;
        }

        public Builder isAlive(boolean isAlive){
            tariff.isAlive=isAlive;
            return this;
        }

        public Builder pricePerM3(double pricePerM3){
            tariff.pricePerM3=pricePerM3;
            return this;
        }

        public Builder pricePerKg(double pricePerKg){
            tariff.pricePerKg=pricePerKg;
            return this;
        }

        public Builder maxWeight(double maxWeight){
            tariff.maxWeight=maxWeight;
            return this;
        }

        public Builder maxVolume(double maxVolume){
            tariff.maxVolume=maxVolume;
            return this;
        }

        public Builder timePer100km(double timePer100km){
            tariff.timePer100km=timePer100km;
            return this;
        }
        public Tariff build(){
            return tariff;
        }

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

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getPricePerM3() {
        return pricePerM3;
    }

    public void setPricePerM3(double pricePerM3) {
        this.pricePerM3 = pricePerM3;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(int pricePerKg) {
        this.pricePerKg = pricePerKg;
    }



    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricePerKm=" + pricePerKm +
                ", pricePerM3=" + pricePerM3 +
                ", pricePerKg=" + pricePerKg +
                ", maxWeight=" + maxWeight +
                ", maxVolume=" + maxVolume +
                ", timePer100km=" + timePer100km +
                '}';
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
