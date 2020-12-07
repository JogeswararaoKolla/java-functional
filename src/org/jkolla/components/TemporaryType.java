package org.jkolla.components;

import java.time.LocalDate;
import java.util.List;

public class TemporaryType {
    Double totalTransAmount;
    Long totalTransCount;
    List<Double> transAmountList;
    LocalDate maxTransDate;
    Double maxTransDateAmount;

    public TemporaryType(Double totalTransAmount, Long totalTransCount, List<Double> transAmountList, LocalDate maxTransDate, Double maxTransDateAmount) {
        this.totalTransAmount = totalTransAmount;
        this.totalTransCount = totalTransCount;
        this.transAmountList = transAmountList;
        this.maxTransDate = maxTransDate;
        this.maxTransDateAmount = maxTransDateAmount;
    }

    @Override
    public String toString() {
        return "TemporaryType{" +
                "totalTransAmount=" + totalTransAmount +
                ", totalTransCount=" + totalTransCount +
                ", transAmountList=" + transAmountList +
                ", maxTransDate=" + maxTransDate +
                ", maxTransDateAmount=" + maxTransDateAmount +
                '}';
    }
}
