package org.jkolla.generics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LowerBounds {
    public static void main(String[] args) {
        List<String> fruits= Stream.of("Apples", "Bananas","Blueberries",
                             "Clementine","Oranges","Watermelon")
                .collect(Collectors.toList());
        fruits.forEach((String s) -> System.out.println(s.toUpperCase()));
        fruits.forEach((Object o) -> {
            if(o.equals("Oranges"))
            System.out.println(o.equals("Oranges"));
        });

        List<Integer> integers=Stream.of(3,4,5,10,20)
                .peek((Integer e) -> System.out.println(e + " as binary string is " + Integer.toBinaryString(e)))
                .peek((Number n) -> System.out.println("The Double value of " + n + " is " + n.doubleValue()))
                .collect(Collectors.toList());
        System.out.println(integers);
        // Here Consumer of Integer or super class of Integer is Number or Object
        // Collection providing the values to the consumer so we use super keyword
        // Peek takes peek(Consumer<? super T> action)
        // PECS "Producer Extends, Consumer Super"
        // The term contravariant preserves the ordering of types more general to more specific
        // Generic Java Collections are contravariant when they use super with a wild Card
    }
}
