package org.jkolla.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args){
        List<String> strings = new ArrayList<>(List.of("one","two","three","four","five"));
        Predicate<String> predicate= (String s) -> s.startsWith("t");
        strings.removeIf(predicate);
        Consumer<String> action= (String s) -> System.out.println(s);
        strings.forEach(action);
    }
}
