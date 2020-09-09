package org.jkolla.lambdas;

import org.jkolla.lambdas.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(){{
            add(new User("Jogi",31));
            add(new User("Sumarchitha",26));
            add(new User("Humisha",1));
            add(new User("Sudhakar",35));
            add(new User("Vanaja",35));
            add(new User("Vanaja",25));
            add(new User("Vasanthi",32));
        }};

        System.out.println(users);
        List<String> names= new ArrayList<>();

        Function<User,String> toName= p -> p.getName();

        users.forEach(u -> names.add(toName.apply(u)));
        names.forEach(System.out::println);

    }
}
