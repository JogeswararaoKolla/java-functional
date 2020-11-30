package org.jkolla.components;

import org.jkolla.models.CustomerTransaction;
import org.jkolla.utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Rollup {
    public static void main(String[] args) {

        List<CustomerTransaction> transactions = new Util().createCustomerTransaction();

        long count = transactions.stream()
                .count();
        System.out.println("count = " + count);

        Map<String, Double> totalTransAmtByCustomerID = transactions
                .stream()
                .collect(Collectors.groupingBy(e -> e.getCustomerId(),
                        Collectors.summingDouble(trans -> trans.getAmount())));

        System.out.println("totalTransAmtByCustomerID = " + totalTransAmtByCustomerID);

        Map<String,Double> sortedTotalTransAmtByCustomerID = new TreeMap<>(Comparator.naturalOrder());
        sortedTotalTransAmtByCustomerID.putAll(totalTransAmtByCustomerID);

        System.out.println("sortedTotalTransAmtByCustomerID = " + sortedTotalTransAmtByCustomerID);

       //totalTransAmtByCustomerID = {C003213=286.61, C008231=174.1, C004221=25.25, C002142=74.45}
       //sortedTotalTransAmtByCustomerID = {C002142=74.45, C003213=286.61, C004221=25.25, C008231=174.1}

    }
}
