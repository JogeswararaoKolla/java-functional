package org.jkolla.models;

public class BodyType {
   private  String item;
   private  Integer quantity;
   private  Double cost;

    public BodyType(String item, Integer quantity, Double cost) {
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
