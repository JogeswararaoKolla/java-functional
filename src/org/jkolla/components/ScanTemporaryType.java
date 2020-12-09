package org.jkolla.components;

import org.jkolla.models.TransactionEvent;

import java.time.LocalDate;

public class ScanTemporaryType extends TransactionEvent {
    int count;

    public ScanTemporaryType(int count) {
        this.count = count;
    }

    public ScanTemporaryType(LocalDate dt, String kind, Double amount, int count) {
        super(dt, kind, amount);
        this.count = count;
    }

    @Override
    public String toString() {
        return "ScanTemporaryType{" +
                "count=" + count + ", " +
                "dt=" + super.getDt() + ", " +
                "kind=" + super.getKind() +  ", " +
                "amount=" + super.getAmount() +
                '}';
    }
}
