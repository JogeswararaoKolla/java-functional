package org.jkolla.lambdas;

import org.jkolla.lambdas.models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>() {
            {
                add(new User("Jogi", 31));
                add(new User("Sumarchitha", 26));
                add(new User("Humisha", 1));
                add(new User("Sudhakar", 35));
                add(new User("Vanaja", 35));
                add(new User("Vanaja", 25));
                add(new User("Vasanthi", 32));
            }
        };

        System.out.println(users);

        // Comparator<User> cmp1 = (p1, p2) -> p1.getName().compareTo(p2.getName());
        // users.sort(cmp1);
        // users.forEach(u -> System.out.println(u));
        // users.forEach(System.out::println);

        // users.sort(Comparator.comparing(user -> user.getName()));
        // users.sort(Comparator.comparing(User::getName).reversed());
        users.sort(Comparator.comparing(User::getName).reversed().thenComparing(User::getAge));

        // users.sort(new Comparator<User>() {
        // @Override
        // public int compare(User o1, User o2) {
        // return o1.getName().compareTo(o2.getName());
        // }
        // });

        Function<User, Integer> f1 = p -> p.getName().length();
        Comparator<User> cmp2 = Comparator.comparing(f1);
        users.sort(cmp2);
        users.forEach(System.out::println);

    }
}
