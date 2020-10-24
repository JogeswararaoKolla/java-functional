package org.jkolla.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapEntrySetDemo {
    public static void main(String[] args) {

        Map<Integer,String> map5 = new HashMap<Integer,String>(){{
            put(850,"Broccoli");
            put(970,"Carrots");
            put(314,"Spinach");
            put(303,"Tomato");
        }};
      
        Map<Integer,String> map6 = new HashMap<>() {{
            put(970,"Cabbage");
            put(314,"Asparagus");
            put(551,"Lettuce");
            put(303,"Tomato");
        }};

        Set<Map.Entry<Integer, String>> map5Set = map5.entrySet();
        Set<Map.Entry<Integer, String>> map6Set = map6.entrySet();

        System.out.println("map5Set = " + map5Set);
        System.out.println("map6Set = " + map6Set);

        // Intersection of the maps
        map5Set.retainAll(map6Set);
        System.out.println("map5Set.retainAll(map6Set) = " + map5Set); // map5Set.retainAll(map6Set) = [303=Tomato]

        System.out.println("map6Set = " + map6Set);

        // Removing the element from map using iterator method.
        Iterator<Map.Entry<Integer, String>> it = map6Set.iterator();
          while(it.hasNext()){
          Map.Entry<Integer,String> entry = it.next();
           if(entry.getKey() == 314) {
            entry.setValue("Updating using setValue");
            it.remove();
            System.out.println("Removing entry it.remove() = " + entry);
       }
   }
   

        System.out.println("remove 314 map6Set" + map6Set); // remove 314 map6Set[551=Lettuce, 970=Cabbage, 303=Tomato]
        System.out.println("remove 314 map6" + map6); // remove 314 map6{551=Lettuce, 970=Cabbage, 303=Tomato}

        map6Set.removeIf(e -> e.getKey() <500); // removeIf takes Predicate and removes elements from the collection which satisfies Predicate.

        System.out.println("removeIf key < 500  map6Set" + map6Set);
        System.out.println("Original Map after removeIf key < 500  map6" + map6);

        // Iterating the map6 using entrySet() method
        for(Map.Entry<Integer,String> entry : map6.entrySet()){
            System.out.println(entry.getKey() + " ; " + entry.getValue());
        }

    }
}
