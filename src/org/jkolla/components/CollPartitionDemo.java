package org.jkolla.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollPartitionDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>() {{
            add("Jogi");
            add("Kolla");
            add("Suma");
            add("Humisha");
            add("Vanaja");
            add("Vasanthi");
        }};

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        UtilFunction<Integer> partitionNumbers = new UtilFunction<>();
        System.out.println("Even Numbers : " + partitionNumbers.getCollSatisfyingCondition(numbers,e -> e % 2 == 0 )); // Even Numbers : [2, 4, 6, 8, 10]
        UtilFunction<String> partition = new UtilFunction<>();
        System.out.println("namesStartsWithV = " + partition.getCollSatisfyingCondition(names,s -> s.startsWith("V") || s.startsWith("S"))); // namesStartsWithV = [Suma, Vanaja, Vasanthi]
        System.out.println("namesStartsWithJ = " + partition.getCollSatisfyingCondition(names,s -> s.startsWith("J"))); // namesStartsWithJ = [Jogi]
        System.out.println("namesStartsWithH = " + partition.getCollSatisfyingCondition(names,s -> s.startsWith("H"))); // namesStartsWithH = [Humisha]

    }
}
