package org.jkolla.interfaces;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    default  Consumer<T> andThen(Consumer<T> after) {
        return (T t) -> {
            this.accept(t);
            after.accept(t);
        };
    }

    default Consumer<T> andBefore(Consumer<T> before){
        return (T t ) -> {
            before.accept(t);
            this.accept(t);
        };
    }
}
