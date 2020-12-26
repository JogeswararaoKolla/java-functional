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

        Map<String, Long> customerIdTotalTransCnt = transactions.stream()
                .collect(Collectors.groupingBy(e -> e.getCustomerId(),
                        Collectors.counting()));
        System.out.println("customerIdTotalTransCnt = " + customerIdTotalTransCnt);

        TreeMap<String, Double> sortedTotalTransAmtByCustomerIdAsc = totalTransAmtByCustomerID.entrySet()
                .stream()
                .collect(() -> new TreeMap<>(Comparator.naturalOrder()),
                        (map1, element) -> map1.put(element.getKey(), element.getValue()),
                        (map2, map3) -> map2.putAll(map3));
        System.out.println("sortedTotalTransAmtByCustomerIdAsc = " + sortedTotalTransAmtByCustomerIdAsc);

        //Here grouping by the customerId and creating an sorted map in Descending order by provided Supplier
        //groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream)
        TreeMap<String, Double> sortedTotalTransAmtByCustomerIdDesc = transactions.stream()
                .collect(
                        Collectors.groupingBy(
                                e -> e.getCustomerId(),
                                () -> new TreeMap<String,Double>(Comparator.reverseOrder()),
                                Collectors.summingDouble(ele -> ele.getAmount())
                        )
                );
        System.out.println("sortedTotalTransAmtByCustomerIdDesc = " + sortedTotalTransAmtByCustomerIdDesc);

        //totalTransAmtByCustomerID = {C003213=286.61, C008231=174.1, C004221=25.25, C002142=74.45}
        //customerIdTotalTransCnt = {C003213=3, C008231=2, C004221=1, C002142=2}
        //sortedTotalTransAmtByCustomerIdAsc = {C002142=74.45, C003213=286.61, C004221=25.25, C008231=174.1}
        //sortedTotalTransAmtByCustomerIdDesc = {C008231=174.1, C004221=25.25, C003213=286.61, C002142=74.45}

    }
}
