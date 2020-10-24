package org.jkolla.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class MapMergeDemo {
    public static void main(String[] args) {

        Map<Integer,String> map1 = new HashMap<>();
        map1.put(850,"Broccoli");
        map1.put(970,"Carrots");
        map1.put(314,"Spinach");
        map1.put(303,"Tomato");
        Map<Integer,String> map2 = new HashMap<>();
        map2.put(970,"Cabbage");
        map2.put(314,"Asparagus");
        map2.put(551,"Lettuce");
        map2.put(303,"Tomato");

        Map<Integer,String>  map3 = new HashMap<>(map1);
        System.out.println("map3 = " + map3);

        Map<Integer,String>  map4 = new HashMap<>(map2);
        System.out.println("map4 = " + map4);

// Merging map4 to map3 using computeIfPresent, computeIfAbsent methods
// default V	computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)
//If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.
//default V	computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)
//If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
//

        map4.forEach((key,value) -> {
         if(map3.containsKey(key))
             map3.computeIfPresent(key, (existingKey,oldValue) -> {
                 System.out.println("Inside computeIfPresent");
                 System.out.println("existingKey = " + existingKey);
                 System.out.println("oldValue = " + oldValue);
                 return   oldValue + "," + value;
             });
         else
         map3.computeIfAbsent(key,e -> {
             System.out.println("Inside the computeIfAbsent");
             System.out.println(e);
             System.out.println(value);
             return value;
         });

     });

 System.out.println("After Compute method calls map3= " + map3);

// default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
// If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.
// Merging Map2 to Map1 if the keys are same, if key doesn't exists it copies  to a comma separated list of values from both maps.

        map2.forEach((k2,v2) -> {
            map1.merge(k2, v2, new BiFunction<String, String, String>() {
                @Override
                public String apply(String s1, String s2) {
                    return s1 + "," + s2;
                }
            });
        });
        System.out.println("After calling merge method map1 = " + map1); // After calling merge method map1 = {850=Broccoli, 970=Carrots,Cabbage,Cabbage, 314=Spinach,Asparagus,Asparagus, 303=Tomato}

    }
}
