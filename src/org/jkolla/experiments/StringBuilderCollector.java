package org.jkolla.experiments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringBuilderCollector {
    public static void main(String[] args) {
        
        List<String> strings = List.of("this", "is", "list", "of", "strings");
        String collect = strings.stream().collect(Collectors.joining(","));
        System.out.println("collect = " + collect);
        // collect = this,is,list,of,strings

        StringBuilder string = strings.stream()
                .parallel()
                .collect(
                () -> new StringBuilder(),
                (sb1, s1) -> sb1.append(s1),
                (sb1, sb2) -> sb1.append(sb2)
        );
        System.out.println("string = " + string.toString());

        // parallel() stream string = thisislistofstrings
        // sequential() stream string = thisislistofstrings
        //  <R> R	collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
        // Performs a mutable reduction operation on the elements of this stream.

        // collect preserves as encountered order of the stream elements in case parallel as well.
        // select collector when doing mutable reduction vs reduce for immutable reduction
        // supplier produces container which we are collecting. Here produces new result object String Builder
        // accumulator function appends input string to the result type String builder
        // Combiner combines the partial results here two string builders are concatenated
        // In execution phase each thread calls supplier (example 4 core it produces 4 String Builder objects) it produces its own string Builder object and appends string for its segment.
        // Then produces partial results and combined using combiner.

    }
}
