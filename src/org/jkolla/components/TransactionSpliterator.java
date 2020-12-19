package org.jkolla.components;

import org.jkolla.models.CustomerTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TransactionSpliterator implements Spliterator<CustomerTransaction> {
    private final Spliterator<String> lineSpliterator;
    private String customerID;
    private LocalDate transDate;
    private double amount;

    public TransactionSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super CustomerTransaction> action) {

        if(this.lineSpliterator.tryAdvance( line -> this.customerID = line) &&
           this.lineSpliterator.tryAdvance(line -> this.transDate = LocalDate.parse(line, DateTimeFormatter.ofPattern("yyyy.MM.dd"))) &&
           this.lineSpliterator.tryAdvance(line -> this.amount = Double.parseDouble(line))) {
            CustomerTransaction transaction = new CustomerTransaction(this.customerID,this.transDate,this.amount);
            action.accept(transaction);
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<CustomerTransaction> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return lineSpliterator.estimateSize()/3;
    }

    @Override
    public int characteristics() {
        return lineSpliterator.characteristics();
    }
}
