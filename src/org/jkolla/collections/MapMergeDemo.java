package org.jkolla.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MapMergeDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.generate(() -> Math.random())
                .mapToInt(p -> (int) (p * 1000))
               // .boxed() // Stream<Integer>
                .mapToObj(Integer::valueOf)
                .limit(10)
                .collect(toList());
        System.out.println("numbers = " + numbers); // numbers = [850, 970, 314, 303, 951, 913, 551, 345, 213, 329]

//        Stream<Integer>	boxed()
//        Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
//        <U> Stream<U>	mapToObj(IntFunction<? extends U> mapper)
//        Returns an object-valued Stream consisting of the results of applying the given function to the elements of this stream.

        Map<Integer,String> map1 = new HashMap<>();
        map1.put(850,"Broccoli");
        map1.put(970,"Carrots");
        map1.put(314,"Spinach");
        map1.put(303,"Tomato");
        Map<Integer,String> map2 = new HashMap<>();
        map2.put(970,"Cabbage");
        map2.put(314,"Asparagus");
        map2.put(551,"Lettuce");

        // Merging Map2 to Map1 if both keys match to a comma separated list of values from both maps.
        for( Map.Entry<Integer,String> entry : map1.entrySet()) {
            if(map2.containsKey(entry.getKey()))
                entry.setValue(entry.getValue() + "," + map2.get(entry.getKey()));
                System.out.println("entry = " + entry);
        }

        System.out.println("Merging by using entry.setValue() method map1 = " + map1); // Merging by using entry.setValue() method map1 = {850=Broccoli, 970=Carrots,Cabbage, 314=Spinach,Asparagus, 303=Tomato}

        // Merging Map2 to Map1 if the keys are same, if key does'nt exists it copies  to a comma separated list of values from both maps.
        map2.forEach((k2,v2) -> {
            map1.merge(k2, v2, new BiFunction<String, String, String>() {
                @Override
                public String apply(String s1, String s2) {
                    return s1 + "," + s2;
                }
            });
        });
        System.out.println("After calling merge method map1 = " + map1); // After calling merge method map1 = {850=Broccoli, 970=Carrots,Cabbage,Cabbage, 314=Spinach,Asparagus,Asparagus, 303=Tomato}

//        default V	merge​(K key, V value, BiFunction<? super V,​? super V,​? extends V> remappingFunction)
//        If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.

    }
}
