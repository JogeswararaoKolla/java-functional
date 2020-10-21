package org.jkolla.interfaces;

public class PredicateDemo {
    public static void main(String[] args) {

        Predicate<String>  isLengthGt5 = t1 -> t1.length() >  5;
        Predicate<String>  isLengthLt20 = s1 -> s1.length() < 20;

        System.out.println("isLengthGt5.test(\"Oranges\") = " + isLengthGt5.test("Oranges")); // calling the predicate by test method.

       Predicate<String> isLengthGt5AndLt20 = isLengthGt5.and(isLengthLt20); // Composing predicates with and default method.

        System.out.println("isLengthGt5AndLt20.negate().test(\"Oranges\") = " + isLengthGt5AndLt20.negate().test("Oranges")); // Inverse the predicate with negate() default method
        System.out.println("isLengthGt5AndLt20.test(\"Apples\") = " + isLengthGt5AndLt20.test("Apples"));

        System.out.println(isLengthGt5.or(String::isBlank).test("Jogi")); // false String length Gt5 or Blank

        System.out.println(isLengthGt5.or(String::isEmpty).test(" ")); // false String length Gt5 or empty

        Predicate<Integer> p1 = Predicate.isEquals(1); // Instance method
        System.out.println( p1.test(1)); // true
        // Lambda Expressions
        // Default methods
        // static methods


    }
}
