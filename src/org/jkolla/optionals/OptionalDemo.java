package org.jkolla.optionals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalDemo {
    public static void main(String[] args) {

        Optional<String> optional= Optional.of("Hello");
        System.out.println("optional = " + optional); // optional = Optional[Hello]
        System.out.println("optional.get() = " + optional.get()); // optional.get() = Hello
        System.out.println("optional.isPresent() = " + optional.isPresent()); // optional.isPresent() = true

        optional =  optional.of("Hello India");
        System.out.println("optional.get() = " + optional.get()); // optional.get() = Hello India

        //static <T> Optional<T>	of(T value)
        //Returns an Optional with the specified present non-null value.
        
        String string = null;
        System.out.println("string = " + null);
        Optional<String> optionalString = Optional.ofNullable(string);
        System.out.println("optionalString = " + optionalString); // optionalString = Optional.empty
       // static <T> Optional<T>	ofNullable(T value)
       // Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
        
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("emptyOptional = " + emptyOptional);
        // static <T> Optional<T>	empty()
        // Returns an empty Optional instance
        // T get() If a value is present, returns the value, otherwise throws NoSuchElementException.
        System.out.println("emptyOptional.isPresent() = " + emptyOptional.isPresent());
        System.out.println("emptyOptional.isEmpty() = " + emptyOptional.isEmpty());
        System.out.println("emptyOptional.orElseGet() = " + emptyOptional.orElseGet(() -> "Jogi"));

        emptyOptional = Optional.of("Hello world");
        System.out.println("emptyOptional.get() = " + emptyOptional.get());

        // <U> Optional<U>	map(Function<? super T,? extends U> mapper)
        // If a value is present, returns an Optional describing (as if by ofNullable(T)) the result of applying the given mapping function to the value,
        // otherwise returns an empty Optional.
        System.out.println(emptyOptional.map( e -> e + " Jogi").orElse("default"));

        Map<Integer,Object> vegetables = new HashMap<>(){{
            put(101,"Carrots");
            put(102,"Tomato");
            put(103,85027);
        }};

        Object value = vegetables.get(104); // value = null
        System.out.println("value = " + value);

        Optional<Object> optionalValue = Optional.ofNullable(vegetables.get(104));
        System.out.println("optionalValue = " + optionalValue); // optionalValue = Optional.empty
        // use method Optional.ofNullable() every time you want to safely transform a value that could be null to an optional.

        OptionalInt first = IntStream.range(100, 2_000_000)
                .map(e -> e * 2)
                .peek((e) -> System.out.println("Element multiply by 2 " + e))
                .filter(e -> e % 3 == 0)
                .peek((e) -> System.out.println("Element Divisible by 3 " + e))
                .findFirst();
        System.out.println("first = " + first); // first = OptionalInt[204]

    }
}
