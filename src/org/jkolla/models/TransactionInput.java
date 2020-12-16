package org.jkolla.models;

public class TransactionInput {
    private Integer transactionId;
    private String customerName;
    private String item;
    private Integer quantity;
    private Double cost;

    public TransactionInput(Integer transactionId, String customerName, String item, Integer quantity, Double cost) {
        this.transactionId = transactionId;
        this.customerName = customerName;
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public String getCustomerName() {
        return customerName;
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
        return "TransactionInput{" +
                "transactionId=" + transactionId +
                ", customerName='" + customerName + '\'' +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
