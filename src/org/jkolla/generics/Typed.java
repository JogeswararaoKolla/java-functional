package org.jkolla.generics;

import java.util.ArrayList;
import java.util.List;

public class Typed {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>(); // Java 5 and 6 declaration
      //  List<String> strings = new ArrayList<>();
      // Java 7 Diamond operator <> on right side data type is not necessary and type is inferred from the assignment (here it is )
        strings.add("hello");
        strings.add("this is Raw");
        strings.add("list of strings");
       // strings.add(25); // Compiler error required type to be  String
       // List<E> List interface is defined to take Element type E.
        for ( String s: strings) {
            System.out.println(s);
        }

        List<Integer> ints= new ArrayList<>();
        ints.add(10);
        ints.add(30); //  compiler gives warning Unnecessary boxing 'new Integer(30)'
        int number1 = ints.get(0); // compiler will unbox values from wrapper class Integer to int
        System.out.println(number1);
    }
}
