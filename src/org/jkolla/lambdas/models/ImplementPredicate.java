package org.jkolla.lambdas.models;

import java.util.List;
import java.util.stream.Collectors;

public class ImplementPredicate {

  public String getNamesOfLength(int length, List<String> names) {
    return names
      .stream()
      .filter(p -> p.length() == length)
      .collect(Collectors.joining(","));
  }

  public String getNamesStartingWith(String c, List<String> list) {
    return list
      .stream()
      .filter(s -> s.startsWith(c))
      .collect(Collectors.joining(","));
  }
}
