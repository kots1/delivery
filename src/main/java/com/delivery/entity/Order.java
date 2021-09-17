package com.delivery.entity;

import java.util.Date;

public class Order {
    private int orderId;
    private Tariff tariff;
    private User user;
    private Direction direction;
    private double price;
    private String description;
    private TypeBaggage typeBaggage;
    private double volume;
    private double weight;
    private String orderStatus;
    private Date orderDate;
    private Date dispatchDate;
    private Date receivingDate;
    private String street;
    private int house;
    private int apartment;

    public void setId(int orderId) {
        this.orderId=orderId;
    }

    public static class Builder {
        private final Order order;

        public Builder() {
            order = new Order();
        }

        public Builder orderId(int orderId){
            order.orderId=orderId;
            return this;
        }
        public Builder tariff(Tariff tariff){
            order.tariff=tariff;
            return this;
        }

        public Builder user(User user){
            order.user=user;
            return this;
        }

        public Builder direction(Direction direction){
            order.direction=direction;
            return this;
        }
        public Builder price(double price){
            order.price=price;
            return this;
        }
        public Builder description(String description){
            order.description=description;
            return this;
        }
        public Builder volume(double volume){
            order.volume=volume;
            return this;
        }
        public Builder weight(double weight){
            order.weight=weight;
            return this;
        }
        public Builder orderStatus(String orderStatus){
            order.orderStatus=orderStatus;
            return this;
        }
        public Builder orderDate(Date orderDate){
            order.orderDate=orderDate;
            return this;
        }
        public Builder dispatchDate(Date dispatchDate){
            order.dispatchDate=dispatchDate;
            return this;
        }
        public Builder typeBaggage(TypeBaggage typeBaggage){
            order.typeBaggage=typeBaggage;
            return this;
        }
        public Builder receivingDate(Date receivingDate){
            order.receivingDate=receivingDate;
            return this;
        }
        public Builder street(String street){
            order.street=street;
            return this;
        }
        public Builder house(int house){
            order.house=house;
            return this;
        }
        public Builder apartment(int apartment){
            order.apartment=apartment;
            return this;
        }
        public Order build(){
            return order;
        }

    }

    public int getOrderId() {
        return orderId;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public User getUser() {
        return user;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getApartment() {
        return apartment;
    }

    public TypeBaggage getTypeBaggage() {
        return typeBaggage;
    }
}
