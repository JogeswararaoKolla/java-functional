package org.jkolla.optionals;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionalDemo {
    public static void main(String[] args) {

        Optional<String> optional= Optional.of("Hello");
        System.out.println("optional = " + optional);
        System.out.println("optional.get() = " + optional.get());
        System.out.println("optional.isPresent() = " + optional.isPresent());

        String string = null;
        System.out.println("string = " + null);
        Optional<String> optionalString = Optional.ofNullable(string);
        System.out.println("optionalString = " + optionalString);

        Optional<String> emptyOptional = Optional.empty();
        System.out.println("emptyOptional = " + emptyOptional);

        System.out.println("emptyOptional.isPresent() = " + emptyOptional.isPresent());

        System.out.println("emptyOptional.isEmpty() = " + emptyOptional.isEmpty());

        System.out.println("emptyOptional.orElseGet() = " + emptyOptional.orElseGet(() -> "Jogi"));

        emptyOptional = Optional.of("Hello world");
        System.out.println("emptyOptional.get() = " + emptyOptional.get());

        OptionalInt first = IntStream.range(100, 2_000_000)
                .map(e -> e * 2)
                .peek((e) -> System.out.println("Element multiply by 2 " + e))
                .filter(e -> e % 3 == 0)
                .peek((e) -> System.out.println("Element Divisible by 3 " + e))
                .findFirst();
        System.out.println("first = " + first); // first = OptionalInt[204]

        BigDecimal sumOfNumbers = Stream.iterate(BigDecimal.ZERO, e -> e.add(BigDecimal.ONE))
                .limit(10)
                .reduce(BigDecimal.ZERO,(accumulator, element) -> accumulator.add(element));
        System.out.println("sumOfNumbers = " + sumOfNumbers);

        int sum = IntStream.iterate(0, e -> e + 1) //  seed and IntUnaryOperator
                .takeWhile(e -> e < 10) // Takes predicate and stops when it satisfies the condition
                .sum();
        System.out.println("sum = " + sum);

        Integer reduce = Stream.iterate(0, e -> e < 100, e -> e + 10) // Stream.iterate with seed, Predicate, UnaryOperator
                .peek(System.out::println)
                .reduce(0, Integer::sum);
        System.out.println("reduce = " + reduce);


    }
}
