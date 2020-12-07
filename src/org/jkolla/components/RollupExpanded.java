package org.jkolla.components;

import org.jkolla.models.CustomerTransaction;
import org.jkolla.utils.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RollupExpanded {
    public static void main(String[] args) {

        List<CustomerTransaction> transactions = new Util().createCustomerTransaction();

        Collector<CustomerTransaction, TemporaryType, TemporaryType> rollupCollector = Collector.of(
                new Supplier<TemporaryType>() {
                    @Override
                    public TemporaryType get() {
                        return new TemporaryType(0.0,0L,new ArrayList<>(),LocalDate.of(1900,01,01),0.0);
                    }
                },
                new BiConsumer<TemporaryType, CustomerTransaction>() {
                    @Override
                    public void accept(TemporaryType temporaryType, CustomerTransaction o) {
                        temporaryType.totalTransAmount = temporaryType.totalTransAmount + o.getAmount();
                        temporaryType.totalTransCount = temporaryType.totalTransCount +  1L;
                        temporaryType.transAmountList.add(o.getAmount());

                        if(temporaryType.maxTransDate.equals(LocalDate.of(1900,01,01))){
                            System.out.println("Initialize Block");
                            System.out.println("CustomerTransaction" + o.toString());
                            System.out.println("temporaryType = " + temporaryType);
                            temporaryType.maxTransDate = o.getTransDate();
                            temporaryType.maxTransDateAmount = o.getAmount();
                            System.out.println("temporaryType = " + temporaryType);
                        }

                        if(o.getTransDate().isAfter(temporaryType.maxTransDate)) {
                            temporaryType.maxTransDate = o.getTransDate();
                            temporaryType.maxTransDateAmount = o.getAmount();
                        }

                    }
                },
                new BinaryOperator<TemporaryType>() {
                    @Override
                    public TemporaryType apply(TemporaryType temporaryType1, TemporaryType temporaryType2) {
                        return null;
                    }
                }
                ,
                new Function<TemporaryType, TemporaryType>() {
                    @Override
                    public TemporaryType apply(TemporaryType temporaryType) {
                        return temporaryType;
                    }
                }
        );

        Map<String, TemporaryType> transactionsByCustomerId = transactions.stream()
                .collect(Collectors.groupingBy(e -> e.getCustomerId(),
                        rollupCollector));
        transactionsByCustomerId.forEach((k,v) -> {
            System.out.println(k + " = " + v);
        });

//  C003213 = TemporaryType{totalTransAmount=286.61, totalTransCount=3, transAmountList=[47.95, 17.42, 221.24], maxTransDate=1995-12-11, maxTransDateAmount=17.42}
//  C008231 = TemporaryType{totalTransAmount=174.1, totalTransCount=2, transAmountList=[122.0, 52.1], maxTransDate=1995-12-10, maxTransDateAmount=52.1}
//  C004221 = TemporaryType{totalTransAmount=25.25, totalTransCount=1, transAmountList=[25.25], maxTransDate=1994-08-15, maxTransDateAmount=25.25}
//  C002142 = TemporaryType{totalTransAmount=74.45, totalTransCount=2, transAmountList=[22.25, 52.2], maxTransDate=1994-06-22, maxTransDateAmount=22.25}

    }
}
