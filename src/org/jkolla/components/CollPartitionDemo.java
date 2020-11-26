package org.jkolla.components;

import java.util.ArrayList;
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

        UtilFunction<String> partition = new UtilFunction<>();
        System.out.println("partition = " + partition.getClass());
        System.out.println("namesStartsWithV = " + partition.getCollSatisfyingCondition(names,s -> s.startsWith("V") || s.startsWith("S"))); // namesStartsWithV = [Suma, Vanaja, Vasanthi]
        System.out.println("namesStartsWithJ = " + partition.getCollSatisfyingCondition(names,s -> s.startsWith("J"))); // namesStartsWithJ = [Jogi]
        System.out.println("namesStartsWithH = " + partition.getCollSatisfyingCondition(names,s -> s.startsWith("H"))); // namesStartsWithH = [Humisha]

    }
}
