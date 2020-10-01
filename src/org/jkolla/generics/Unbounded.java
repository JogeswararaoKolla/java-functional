package org.jkolla.generics;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Unbounded {
    public static void main(String[] args) {
        List<?> values = Arrays.asList("Hello","Jogi");
        System.out.println(values.size());
        System.out.println(values); // [Hello, Jogi]
        //   boolean containsAll(Collection<?> c);
        System.out.println(values.containsAll(Arrays.asList("Hello","Kolla"))); // false
        values.forEach((Object o) -> System.out.println(o));
       // values.add(10); // we get compile error as we can't add elements to ? unknown type.
        List<Integer> integers = Arrays.asList(10,30,40);
        System.out.println(integers); // [10, 30, 40]
        // integers.add(50); // java.lang.UnsupportedOperationException occurs we can't add elements to integers because Arrays.asList() creates an unmodifiable list
        integers.set(1,50);
        System.out.println(integers); // [10, 50, 40]
    }
}
