package org.jkolla.streams;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamUtilDemo {
    public static void main(String[] args) {

// Stream<Integer>	boxed()  Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
// <U> Stream<U>	mapToObj(IntFunction<? extends U> mapper)
// Returns an object-valued Stream consisting of the results of applying the given function to the elements of this stream.

        //Creating List<Integer> using IntStream mapToObj method to convert from primitive type to  Wrapper class.
        int[] numbers = {1,2,3,4,5,6,8,9,10};   // Creating IntStream using Arrays.stream() method
        List<Integer>  ints = Arrays.stream(numbers)
                                    //.boxed() // IntStream has boxed method
                                      .mapToObj(Integer::valueOf)
                                      .collect(Collectors.toList());
        System.out.println("ints = " + ints);
// Just as mapToInt, mapToLong, mapToDouble parse stream of objects into the associated primitives, 
// the mapToObj method from IntStream, LongStream, and DoubleStream converts primitives to instances associated with the wrapper class. 

        //Creating List<Integer> using IntStream instance boxed() method.
        List<Integer> integersNumbers1 = Stream.generate(() -> Math.random())
                .mapToInt(new ToIntFunction<Double>() {
                    @Override
                    public int applyAsInt(Double value) {
                        return (int) (value * 1000);
                    }
                })
                .boxed()
                .limit(10)
                .collect(toList());
        System.out.println("integersNumbers = " + integersNumbers1);

        //Creating List<Integer> using three-argument version of collect method.
        List<Integer>  integersNumbers2 = IntStream.of(1, 5, 7, 8, 10)
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
               // .collect(() -> new ArrayList<Integer>(), (list, element) -> list.add(element), (list1, list2) -> list1.addAll(list2));
        System.out.println("integersNumbers2 = " + integersNumbers2);

        // Creating Stream using Stream.iterate method
        Stream.iterate("+", e -> e + "+")
                .limit(5)
                .forEach(System.out::println);

    }
}
