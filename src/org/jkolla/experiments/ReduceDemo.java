package org.jkolla.experiments;

import java.util.stream.Stream;

public class ReduceDemo {
    public static void main(String[] args) {

        Stream<String> list = Stream.of("this", "is", "list", "of", "strings");

        String string = list.sequential().reduce("",(s1, s2) -> s1 + "," +  s2,
                (s1,s2) ->  s1 + "," + s2);
        // parallel() stream  string = ,this,,is,,list,,of,,strings
        // sequential() stream string = ,this,is,list,of,strings

        System.out.println("string = " + string);

        // Strings are Immutable type
        // Immutable reduction produces a new string for every concat operation.
        // temporary objects are created for every +
        // reduce preserves as encountered order of the stream elements.
    }
}
