package org.jkolla.models;

import java.time.LocalDate;

public class CustomerTransaction {

    private String customerId;
    private LocalDate transDate;
    private Double amount;

    public CustomerTransaction(String customerId, LocalDate transDate, Double amount) {
        this.customerId = customerId;
        this.transDate = transDate;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "CustomerTransaction{" +
                "customerId='" + customerId + '\'' +
                ", transDate=" + transDate +
                ", amount=" + amount +
                '}';
    }
}
