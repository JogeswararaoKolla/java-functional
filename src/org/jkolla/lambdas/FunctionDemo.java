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
    }
}
