package org.jkolla.models;

public class TrailerType {
    private Integer totalQuantity;
    private Double totalCost;

    public TrailerType(Integer totalQuantity, Double totalCost) {
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "TrailerType{" +
                "totalQuantity=" + totalQuantity +
                ", totalCost=" + totalCost +
                '}';
    }
}
