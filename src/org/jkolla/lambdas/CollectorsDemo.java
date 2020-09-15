package org.jkolla.lambdas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//        for(User user : users){
//            List<User> list= null;
//            if(groupByName.containsKey(user.getName())){
//                list = groupByName.get(user.getName());
//            }
//            else {
//                list = new ArrayList<User>();
//                groupByName.put(user.getName(),list);
//            }
//            list.add(user);
//        }
//        System.out.println(groupByName);

       // groupingBy (Function<T,R>)
        Map<String,List<User>> byName= users.stream()
                .collect(Collectors.groupingBy(User::getName));
        System.out.println(byName);
        // groupingBy (Function<T,R>,Collector)
        Map<String, List<Integer>> byNameAge= users.stream()
                .collect(Collectors.groupingBy(User::getName,Collectors.mapping(User::getAge,Collectors.toList())));
        System.out.println(byNameAge);
        // Collector(Function,Collector(Function,Collector))
//        Map<String,Long> countByName=users.stream()
//                .collect(Collectors.groupingBy(User::getName,Collectors.counting()));
//        System.out.println(countByName);
        // groupingBy and mapping both took (Function ,Collector)
        // collectingAndThen (Collector,Function)
        // apply collector and a function with it

         Map<String,Integer> countByName= users.stream()
                 .collect(Collectors.groupingBy(User::getName,
                         Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));
        System.out.println(countByName);

    }
}
