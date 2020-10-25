package org.jkolla.streams;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMapDemo {
    public static void main(String[] args) {

        Stream<String> names = Stream.of("Jogi", "Humisha", "Suma");
        record Person(String name){} // Declare Java class using Java 14 preview feature

        Function<String, Person> function = new Function<String, Person>() {
            @Override
            public Person apply(String s) {
                return new Person(s);
            }
        };

        List<Person> collect = names
                .map(e -> function.apply(e)) // calling function with apply method
             // .map(s -> new Person(s)) // Using Lambda Expression
             // .map(Person::new) // using method reference
                .collect(Collectors.toList()); // Stream<T>.map(Function one to one) ==> Stream<R>
        System.out.println("collect = " + collect); // collect = [Person[name=Jogi], Person[name=Humisha], Person[name=Suma]]

    }
}
