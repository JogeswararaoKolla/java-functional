package org.jkolla.lambdas;

import org.jkolla.lambdas.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>() {
            {
                add(new User("Jogi", 31));
                add(new User("Sumarchitha", 26));
                add(new User("Humisha", 1));
                add(new User("Sudhakar", 35));
                add(new User("Vanaja", 35));
                add(new User("Vasanthi", 32));
            }
        };

        // System.out.println(users);
        List<String> names = new ArrayList<>();
        /*
         * A Lambda Expression implements a Functional Interface Functional interface
         * has only one abstract method default and static methods do not count methods
         * from object class toString, equals do not count Functional interface is
         * annotated with @FunctionalInterface
         */
        // @FunctionalInterface
        // public interface Function<T, R> {
        // R apply(T t);
        // default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        // Objects.requireNonNull(after);
        // return (T t) -> after.apply(apply(t));
        // }
        // default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        // Objects.requireNonNull(before);
        // return (V v) -> apply(before.apply(v));
        // }
        // static <T> Function<T, T> identity() {
        // return t -> t;
        // }
        // }

        Function<User, String> toName = p -> p.getName();

        users.forEach(u -> names.add(toName.apply(u)));
        // names.forEach(System.out::println);
        // get list og names in uppercase of those who are older than 30

        List<String> namesOlder30 = users.stream().filter(p -> p.getAge() > 30).map(m -> m.getName())
                .map(String::toUpperCase)
                // .forEach( e -> System.out.println(e))
                .collect(Collectors.toList());
        System.out.println(namesOlder30);

        Map<String, Integer> nameAndAge = new HashMap<>();

        for (User u : users) {
            nameAndAge.put(u.getName(), u.getAge());
        }
        System.out.println(nameAndAge);

        System.out.println(users.stream().collect(
                // Collectors.toMap(keyMapper, valueMapper)
                // Collectors.toMap(u -> u.getName(), u -> u.getAge())
                Collectors.toMap(User::getName, User::getAge)));

        List<Integer> ages = users.stream().map(User::getAge).collect(Collectors.toList());
        // .collect(Collectors.toUnmodifiableList());
        // ages.add(99); Error when using toUnmodifiableList()
        System.out.println(ages);
        System.out.println(ages.getClass());
        System.out.println(users.stream().filter(p -> p.getAge() > 30).map(p -> p.getName()).map(String::toUpperCase)
                .collect(Collectors.joining(",")));

        System.out.println(users.stream().collect(Collectors.partitioningBy(p -> p.getAge() % 2 == 0)));
        // {false=[User{name=Jogi, age=31}, User{name=Humisha, age=1},
        // User{name=Sudhakar, age=35}, User{name=Vanaja, age=35}],
        // true=[User{name=Sumarchitha, age=26}, User{name=Vasanthi, age=32}]}

        // one-to-one function
        List<Integer> numbers = List.of(1, 2, 3);
        System.out.println(numbers.stream().map(element -> element * 2) // Function<T,R> ==> Stream<R>
                .collect(Collectors.toList()));
        // result : [2, 4, 6]
        // Stream<T>.map(Function one to one) ==> Stream<R>

        // one-to-many function
        System.out.println(numbers.stream().map(e -> List.of(e - 1, e + 1)) // one to many function
                .collect(Collectors.toList()));
        // if you have one to many function , use map to go from Stream<T>.map(f1n) ==>
        // Stream<Collection<R>>
        // result : [[0, 2], [1, 3], [2, 4]]

        // Stream<Stream<Integer>>
        System.out.println(numbers.stream() // Stream<Integer>
                .flatMap(e -> List.of(e - 1, e + 1).stream()) // Stream<Stream<Integer>>
                // Function<T,Stream<R>) ==> Stream<R>
                .collect(Collectors.toList()));
        // if you have one to many function, use flatMap to go Stream<T>.flatMap(f1n)
        // ==> Stream<R>
        // result : [0, 2, 1, 3, 2, 4]

    }
}
