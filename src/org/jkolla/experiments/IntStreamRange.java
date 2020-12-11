package org.jkolla.experiments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamRange {
    public static void main(String[] args) {

        List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());

        List<Integer>  list1 = IntStream.range(0, 20) // Returns primitive int IntStream
                .parallel()
                .boxed()
                .peek(element -> list2.add(element))
                .collect(Collectors.toList());

        // Stream<Integer>	boxed() Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
        // collect Collectors.toList() preserves the encountered order of the input elements when executed in parallel and captures the result.
        // synchronizedList here list2 order of the input elements is not preserved.
        // processing order is completely orthogonal when compared to the input Elements encountered order when executing in parallel.
        System.out.println("list1 = " + list1); // list1 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
        System.out.println("list2 = " + list2); // list2 = [17, 6, 2, 12, 4, 19, 5, 3, 18, 16, 1, 8, 15, 0, 9, 7, 11, 10, 14, 13]
        
    }
}
