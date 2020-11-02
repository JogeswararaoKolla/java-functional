package org.jkolla.interfaces;

import java.util.ArrayList;
import java.util.List;

public class ConsumerDemo {
    public static void main(String[] args) {

        Consumer<List<String>> c1 = (List<String> strings) -> strings.add("c1 Called");
        Consumer<List<String>> c2 = (List<String> strings) -> strings.add("c2 called");
        
        Consumer<List<String>> c3 = c1.andThen(c2);
        
        List<String> strings1 = new ArrayList<>(){{
            add("Hello");
        }};
        
        c3.accept(strings1);
        System.out.println("strings1 = " + strings1); // strings1 = [Hello, c1 Called, c2 called]
        
        Consumer<List<String>>  c4 = c1.andBefore(c2);

        List<String> strings2 = new ArrayList<>(){{
            add("Welcome");
        }};
        
        c4.accept(strings2);
        System.out.println("strings2 = " + strings2); // strings2 = [Welcome, c2 called, c1 Called]

    }
}
