package org.jkolla.recipes;

public class RollupTemporaryType {
    Integer numItems;
    Integer totalQuantity;
    Double totalCost;
    String customerName;

    public RollupTemporaryType(Integer numItems, Integer totalQuantity, Double totalCost, String customerName) {
        this.numItems = numItems;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "RollupTemporaryType{" +
                "numItems=" + numItems +
                ", totalQuantity=" + totalQuantity +
                ", totalCost=" + totalCost +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
