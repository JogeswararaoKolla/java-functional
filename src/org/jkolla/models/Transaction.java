package org.jkolla.models;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
    private String customerId;
    private String transactionId;
    private Integer cardNumber;
    private Date transactionDate;
    private Double transAmount;
    private String transDescription;

    public Transaction(String customerId, String transactionId, Integer cardNumber, Date transactionDate, Double transAmount, String transDescription) {
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
        this.transactionDate = transactionDate;
        this.transAmount = transAmount;
        this.transDescription = transDescription;
    }

    public Transaction() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }

    @Override
    public int compareTo(Transaction o) {
        return customerId.compareToIgnoreCase(o.customerId);
    }
}
