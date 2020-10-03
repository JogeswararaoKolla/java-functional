package org.jkolla.collectors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class groupingByDemo {
    public static void main(String[] args) {
        List<String> strings= List.of("one","two","three","four","five","six","seven","eight","nine","ten");

        Map<Integer, List<String>> stringsByLength=
        strings.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(stringsByLength);
        System.out.println();

        Map<Integer , Long> stringsByTotal =strings.stream()
        .collect(Collectors.groupingBy((String s ) -> s.length(), Collectors.counting()));
        System.out.println(stringsByTotal);
    }
}
