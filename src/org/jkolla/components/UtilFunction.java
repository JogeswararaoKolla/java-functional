package org.jkolla.components;
import java.util.Objects;
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
}
