package org.jkolla.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {
    public static void main(String[] args) {

        Collection<String> list =  new ArrayList<>();

        list.add("hello");
        list.add("Jogi");
        list.add("Kolla");
        list.add("empty");

        System.out.println("list : " + list);

        System.out.println(list.remove("empty"));

        System.out.println("list.remove(Object o) :" + list);

        for(String e : list){
            System.out.println("element : " + e);
          //  list.remove(e); // ConcurrentModificationException
        }
        System.out.println("list size : " + list.size());

        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String e = it.next();
            System.out.println("e = " + e);
            it.remove(); // Iterator method to remove elements from the list
        }
        System.out.println(list);
    }
}
