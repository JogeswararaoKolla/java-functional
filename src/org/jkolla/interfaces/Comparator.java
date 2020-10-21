package org.jkolla.interfaces;

import java.util.function.Function;

@FunctionalInterface
public interface Comparator<T> {
    int compare(T t1, T t2) ;

    public static <U> Comparator<U> comparing(Function<U, ? extends Comparable > f) {
        return (t1,t2) -> f.apply(t1).compareTo(f.apply(t2));
    }

    public default  Comparator<T> thenComparing(Comparator<T> cmp) {
        return (t1,t2) -> compare(t1,t2) == 0 ? cmp.compare(t1,t2) : compare(t1,t2);
    }

    public default  Comparator<T> thenComparing(Function<T, ? extends Comparable > f) {
        return (t1,t2) -> compare(t1,t2) == 0 ? f.apply(t1).compareTo(f.apply(t2)) : compare(t1,t2);
    }

}
