package org.jkolla.models;

public class HeaderBodyTrailerFlatRecordType {
    private String kind;
    private Integer transactionId;
    private String  customerName;
    private Integer numItems;
    private String item;
    private Integer quantity;
    private Double cost;
    private Integer totalQuantity;
    private Double totalCost;

    public HeaderBodyTrailerFlatRecordType() {
    }

    public HeaderBodyTrailerFlatRecordType(String kind, Integer transactionId, String customerName, Integer numItems, String item, Integer quantity, Double cost, Integer totalQuantity, Double totalCost) {
        this.kind = kind;
        this.transactionId = transactionId;
        this.customerName = customerName;
        this.numItems = numItems;
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    public String getKind() {
        return kind;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getNumItems() {
        return numItems;
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

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "HeaderBodyTrailerFlatRecordType{" +
                "kind='" + kind + '\'' +
                ", transactionId=" + transactionId +
                ", customerName='" + customerName + '\'' +
                ", numItems=" + numItems +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", totalQuantity=" + totalQuantity +
                ", totalCost=" + totalCost +
                '}';
    }
}
