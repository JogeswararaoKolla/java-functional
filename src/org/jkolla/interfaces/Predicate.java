package org.jkolla.interfaces;

import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> negate() {
        return  t -> ! test(t);
    }

    default Predicate<T>  and(Predicate<T> other) {
        Objects.requireNonNull(other);
        return t -> test(t) && other.test(t);
    }

    default Predicate<T> or(Predicate<T> other) {
        Objects.requireNonNull(other);
        return t -> test(t) || other.test(t);
    }

    static  <U> Predicate<U> isEquals(U u ) {
        return t -> t.equals(u);
    }

}
