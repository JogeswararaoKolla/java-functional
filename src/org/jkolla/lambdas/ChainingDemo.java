package org.jkolla.lambdas;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ChainingDemo {
    public static void main(String[] args) {
        Consumer<String> c1 = s -> System.out.println("c1 consumes " + s);
        Consumer<String> c2 = s -> System.out.println("c2 consumes " + s);
        Consumer<String> c3 = s -> {
            c1.accept(s);
            c2.accept(s);
        };
        c3.accept("hello from c3");
        Consumer<String> c4 = c1.andThen(c2);
        c4.accept("hello from c4");

        Predicate<String> isNull = s -> s == null;
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("For Hello = " + isEmpty.test("Hello"));
        System.out.println("For Empty = " + isEmpty.test(""));
        System.out.println("For null = " + isNull.test(null));
        System.out.println("For Hello is null check = " + isNull.test("Hello"));

        Predicate<String> p = isNull.negate().and(isEmpty.negate());
        System.out.println("For Empty = " + p.test(""));
        System.out.println("For Hello = " + p.test("Hello"));
    }
}
