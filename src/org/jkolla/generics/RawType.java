package org.jkolla.generics;

import java.util.ArrayList;
import java.util.List;

public class RawType {
    public static void main(String[] args) {
        List strings = new ArrayList<>();
        strings.add("hello");
        strings.add("this is Raw");
        strings.add("list of strings");
        // strings.add(25);

        System.out.println(strings);

        for(Object o : strings)
        {
            String s = (String) o;
            System.out.printf("%s has %d characters%n",s, s.length());
        }

        //Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
        // at org.jkolla.generics.RawType.main(RawType.java:18)

    }
}
