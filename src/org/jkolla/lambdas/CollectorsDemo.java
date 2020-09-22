package org.jkolla.lambdas;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jkolla.lambdas.models.User;

public class CollectorsDemo {
        public static List<User> createData() {
                return List.of(new User("Mohan Matta", 29), new User("Sudarsan", 32), new User("sriram", 33),
                                new User("Noor", 27), new User("Noor", 20), new User("Mohan Matta", 20));
        }

        public static void main(String[] args) {

                List<User> users = createData();

                users.forEach(System.out::println);

                Map<String, List<User>> groupByName = new HashMap<>();
                // group the users based on their name
                // for(User user : users){
                // List<User> list= null;
                // if(groupByName.containsKey(user.getName())){
                // list = groupByName.get(user.getName());
                // }
                // else {
                // list = new ArrayList<User>();
                // groupByName.put(user.getName(),list);
                // }
                // list.add(user);
                // }
                // System.out.println(groupByName);

                // groupingBy (Function<T,R>)
                Map<String, List<User>> byName = users.stream().collect(Collectors.groupingBy(User::getName));
                System.out.println(byName);
                // groupingBy (Function<T,R>,Collector)
                Map<String, List<Integer>> byNameAge = users.stream().collect(Collectors.groupingBy(User::getName,
                                Collectors.mapping(User::getAge, Collectors.toList())));
                System.out.println(byNameAge);
                // Collector(Function,Collector(Function,Collector))
                // Map<String,Long> countByName=users.stream()
                // .collect(Collectors.groupingBy(User::getName,Collectors.counting()));
                // System.out.println(countByName);
                // groupingBy and mapping both took (Function ,Collector)
                // collectingAndThen (Collector,Function)
                // apply collector and a function with it

                Map<String, Integer> countByName = users.stream().collect(Collectors.groupingBy(User::getName,
                                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
                System.out.println(countByName);

                List<String> actors = Arrays.asList("Chiranjeevi", "Ramcharan", "Prabhas", "NTR", "Pavan kalyan",
                                "Vikram", "Naga Chaitanya", "Allu Arjun", "Mahesh Babu");

                Map<String, Integer> actorsToLength = actors.stream()
                                // .collect(Collectors.toMap(name -> name,name -> name.length()));
                                .collect(Collectors.toMap(Function.identity(), String::length));
                System.out.println(actorsToLength);

                Map<Character, List<String>> byFirstLetter = actors.stream()
                                .collect(Collectors.groupingBy(name -> name.charAt(0)));
                System.out.println(byFirstLetter);

                // rather than a list of name for each starting letter, what if we want a list
                // of length of names?
                System.out.println(actors.stream().collect(Collectors.groupingBy(name -> name.charAt(0),
                                Collectors.mapping(String::length, Collectors.toList()))));
                // groupingBy gives a Collector
                // groupingBy takes a Collector for example mapping
                // mapping takes a Collector for example toList
                // recursive nature

                System.out.println(users.stream().collect(Collectors.groupingBy(User::getAge, Collectors.mapping(
                                p -> p.getName().toUpperCase(),
                                Collectors.flatMapping(e -> Stream.of(e.split("")), Collectors.toSet())))));
                // flatMapping is Mapping and then Flattening
                // mapping(Function mapper , Collector downstream )
                // result : {32=[A, R, S, D, U, N], 33=[A, R, S, I, M], 20=[ , A, R, T, H, M, N,
                // O], 27=[R, N, O], 29=[ , A, T, H, M, N, O]}
        }

}
