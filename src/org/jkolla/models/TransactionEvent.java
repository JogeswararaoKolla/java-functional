package org.jkolla.models;

import java.time.LocalDate;

public class TransactionEvent {

  private  LocalDate dt;
  private  String kind;
  private  Double amount;

    public TransactionEvent() {
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTransactionEvent (TransactionEvent transactionEvent) {
        this.dt = transactionEvent.dt;
        this.kind = transactionEvent.kind;
        this.amount = transactionEvent.amount;
    }

    public TransactionEvent(LocalDate dt, String kind, Double amount) {
        this.dt = dt;
        this.kind = kind;
        this.amount = amount;
    }

    public LocalDate getDt() {
        return dt;
    }

    public String getKind() {
        return kind;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TransactionEvent{" +
                "dt=" + dt +
                ", kind='" + kind + '\'' +
                ", amount=" + amount +
                '}';
    }
}

