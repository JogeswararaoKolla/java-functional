package org.jkolla.lambdas;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerDemo {

  public static void main(String[] args) {
    Supplier<String> supplier = () -> {
      System.out.println("I am inside supplier");
      return "Hello from supplier";
    };
    String string = supplier.get();
    System.out.println(string);

    Consumer<String> consumer = (String t) -> {
      System.out.println("I am inside the Consumer");
      System.out.println(t);
    };
    consumer.accept("Hello from Consumer");
  }
}
