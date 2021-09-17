package com.delivery.entity;

public class Direction {
    private int id;
    private int distance;
    private String finalCity;
    private String startCity;
    private boolean  isAlive;


    public Direction(int distance) {
        this.distance = distance;
    }


    public Direction(int id, int distance, String finalCity, String startCity) {
        this.id = id;
        this.distance = distance;
        this.finalCity = finalCity;
        this.startCity = startCity;
    }

    public String getFinalCity() {
        return finalCity;
    }

    public void setFinalCity(String finalCity) {
        this.finalCity = finalCity;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", distance=" + distance +
                ", finalCity='" + finalCity + '\'' +
                ", startCity='" + startCity + '\'' +
                ", isAlive=" + isAlive +
                '}';
    }
}
