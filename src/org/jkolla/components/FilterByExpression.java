package org.jkolla.components;

import org.jkolla.models.CustomerTransaction;
import org.jkolla.utils.Util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByExpression {
    public static void main(String[] args) {

        List<CustomerTransaction> transactions = new Util().createCustomerTransaction();
        System.out.println("transactions = " + transactions.size());
        
        List<CustomerTransaction> collect = transactions.stream()
                .peek(System.out::println)
                .filter(e -> e.getTransDate().isAfter(LocalDate.parse("1995-01-01")))
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println("After Filtering the Data");
        collect.forEach(System.out::println);
        // CustomerTransaction{customerId='C003213', transDate=1995-12-11, amount=17.42}
        // CustomerTransaction{customerId='C008231', transDate=1995-12-10, amount=52.1}
    }
}
