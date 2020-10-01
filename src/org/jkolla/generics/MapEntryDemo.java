package org.jkolla.generics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapEntryDemo {
    public static void main(String[] args) {
        Map<Integer,String> vegetables= new HashMap<>();
        vegetables.put(501,"Broccoli");
        vegetables.put(120,"Carrots");
        vegetables.put(150,"Spinach");
        vegetables.put(400,"Tomato");
        vegetables.put(205,"Kale");
        vegetables.put(505,"Carrots");

        System.out.println("vegetables: " + vegetables);
        Set<Map.Entry<Integer,String>>  vegetablesSet= vegetables.entrySet();
        System.out.println("vegetablesSet: " + vegetablesSet);
        System.out.println();
        for(Map.Entry<Integer,String> entry:vegetablesSet){
            if(entry.getKey() < 200 )
               entry.setValue(entry.getValue().toUpperCase());
            // this update effects vegetables map and also vegetablesSet as it is view of the original map vegetables.
        }
        System.out.println("After entry.setValue()  method call");
        System.out.println("vegetables: " + vegetables);
        System.out.println("vegetablesSet: " + vegetablesSet);

        // Sorting Maps
        System.out.println();
        System.out.println("Sorted by Key");
        vegetables.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach( entry ->
                        System.out.println(entry.getKey() + ";" + entry.getValue()));
//        Interface Map.Entry<K,V>
//        static <K extends Comparable<? super K>,V>
//        Comparator<Map.Entry<K,V>>	comparingByKey() Returns a comparator that compares Map.Entry in natural order on key.

        System.out.println();
        System.out.println("Sorted by Value Descending order");
        vegetables.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach( entry ->
                        System.out.println(entry.getKey() + ";" + entry.getValue()));
//        static <K,V extends Comparable<? super V>>
//        Comparator<Map.Entry<K,V>>	comparingByValue()  Returns a comparator that compares Map.Entry in natural order on value.

    }
}
