package org.jkolla.models;

public class HeaderType {
    private Integer transactionId;
    private String  customerName;
    private Integer numItems;

    public HeaderType(Integer transactionId, String customerName, Integer numItems) {
        this.transactionId = transactionId;
        this.customerName = customerName;
        this.numItems = numItems;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getNumItems() {
        return numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    @Override
    public String toString() {
        return "HeaderType{" +
                "transactionId=" + transactionId +
                ", customerName='" + customerName + '\'' +
                ", numItems=" + numItems +
                '}';
    }
}
