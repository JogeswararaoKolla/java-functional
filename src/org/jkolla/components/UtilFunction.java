package org.jkolla.components;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import java.util.List;
import java.util.stream.Collectors;

public class UtilFunction<T> {
    public List<T> getCollSatisfyingCondition(List<T> coll, Predicate<T> selector) {
        return coll.stream()
                .filter(selector)
                .collect(Collectors.toList());
    }

    public  List<T> getCollNonNullElements(List<T> coll) {
        return coll.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public <R> List<R> getCollMap(List<T> coll, Function<T,R> func) {
        List<R> result = new ArrayList<>();
        for(T t : coll) {
            result.add(func.apply(t));
        }
        return result;
    }
}
