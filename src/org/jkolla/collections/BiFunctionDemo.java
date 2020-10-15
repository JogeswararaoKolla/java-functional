package org.jkolla.collections;

import org.jkolla.models.Customer;
import org.jkolla.models.CustomerMapper;
import org.jkolla.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BiFunctionDemo {
    public static void main(String[] args) {
        List<Customer> customers = (new Util()).createCustomers();

        Map<Integer, String> customerMap1 = customers.stream()
                .limit(3)
                .collect(
                        Collectors.toMap(
                                Customer::getCustomerId,
                                Customer::getFirstName
                        )
                );
        System.out.println("customerMap1 = " + customerMap1); // customerMap1 = {1600074238=NICK, 1600092791=COURTNEY, 1600073044=LUKE}

        Map<Integer, String> customerMap2 = customers.stream()
                .skip(1)
                .limit(3)
                .collect(
                        Collectors.toMap(
                                Customer::getCustomerId,
                                Customer::getCity

                        )
                );

        System.out.println("customerMap2 = " + customerMap2); // customerMap2 = {1600074238=DALLAS, 1600073044=DETROIT, 1600086354=HOUSTON}

        BiFunction< Map<Integer, String>,  Map<Integer, String>,  Map<Integer, CustomerMapper>> biFunction = new BiFunction<Map<Integer, String>, Map<Integer, String>, Map<Integer, CustomerMapper>>() {
            @Override
            public Map<Integer, CustomerMapper> apply(Map<Integer, String> map1, Map<Integer, String> map2) {
               return map1.entrySet()
                        .stream()
                        .filter( e -> map2.containsKey(e.getKey()))
                        .map( e -> new CustomerMapper(e.getKey(),e.getValue(),map2.get(e.getKey())))
                        .collect(Collectors.toMap(e -> e.getCustomerId(),
                                Function.identity()));

            }
        };
        Map<Integer, CustomerMapper> result = biFunction.apply(customerMap1,customerMap2);
        System.out.println("result = " + result); // result = {1600074238=CustomerMapper{customerId=1600074238, firstName='NICK', city='DALLAS'}, 1600073044=CustomerMapper{customerId=1600073044, firstName='LUKE', city='DETROIT'}}

    }
}
