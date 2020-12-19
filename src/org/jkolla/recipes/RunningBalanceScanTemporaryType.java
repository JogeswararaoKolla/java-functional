package org.jkolla.recipes;

import org.jkolla.models.TransactionInput;

public class RunningBalanceScanTemporaryType extends TransactionInput {
    Double runningBalance;

    public RunningBalanceScanTemporaryType(Integer transactionId, String customerName, String item, Integer quantity, Double cost, Double runningBalance) {
        super(transactionId, customerName, item, quantity, cost);
        this.runningBalance = runningBalance;
    }

    public Double getRunningBalance() {
        return runningBalance;
    }

    @Override
    public String toString() {
        return "RunningBalanceScanTemporaryType{" +
                "transactionId=" + super.getTransactionId() +
                ", customerName='" + super.getCustomerName() + '\'' +
                ", item='" + super.getItem() + '\'' +
                ", quantity=" + super.getQuantity() +
                ", cost=" + super.getCost() +
                ", runningBalance=" + runningBalance +
                '}';
    }
}
