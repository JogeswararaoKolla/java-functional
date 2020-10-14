package org.jkolla.optionals;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {

        Optional<String> optional= Optional.of("Hello");
        System.out.println("optional = " + optional);
        System.out.println("optional.get() = " + optional.get());
        System.out.println("optional.isPresent() = " + optional.isPresent());

        String string = null;
        System.out.println("string = " + string);
        Optional<String> optionalString = Optional.ofNullable(string);
        System.out.println("optionalString = " + optionalString);

        Optional<String> emptyOptional = Optional.empty();
        System.out.println("emptyOptional = " + emptyOptional);

        System.out.println("emptyOptional.isPresent() = " + emptyOptional.isPresent());

        System.out.println("emptyOptional.isEmpty() = " + emptyOptional.isEmpty());

        emptyOptional = Optional.of("Hello world");
        System.out.println("emptyOptional.get() = " + emptyOptional.get());
    }
}
