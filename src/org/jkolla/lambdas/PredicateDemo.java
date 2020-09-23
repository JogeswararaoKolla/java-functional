package org.jkolla.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jkolla.lambdas.models.*;

public class PredicateDemo {

  public static void main(String[] args) {
    List<String> strings = new ArrayList<>(
      List.of("one", "two", "three", "four", "five")
    );

    // public interface Predicate<T> {
    // boolean test(T t);
    // default Predicate<T> and(Predicate<? super T> other) {
    // Objects.requireNonNull(other);
    // return (t) -> test(t) && other.test(t);
    // }
    // default Predicate<T> negate() {
    // return (t) -> !test(t);
    // }
    // }
    Predicate<String> predicate = (String s) -> s.startsWith("t");
    strings.removeIf(predicate);
    System.out.println(strings);
    // [one, four, five]
    Consumer<String> action = (String s) -> System.out.println(s);
    strings.forEach(action);

    ImplementPredicate demo = new ImplementPredicate();
    System.out.println(
      demo.getNamesOfLength(4, List.of("Jogi", "Suma", "Joe"))
    );
    // result : Jogi,Suma
    System.out.println(
      demo.getNamesStartingWith("J", List.of("Jogi", "Joe", "Suma", "Vanaja"))
    );
    // result : Jogi,Joe
  }
}
