package org.jkolla.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FactoryMethods {
    public static void main(String[] args) {
        List<String> countries = new ArrayList<>();
        countries.add("India");
        countries.add("Germany");

        // Java 9 
        List<String> countriesImmutable = List.of("India", "USA");
        System.out.println("countriesImmutable = " + countriesImmutable);
        // countriesImmutable.add("Japan"); // UnsupportedOperationException throws at Runtime

        Map<Integer, String> vegetablesMap = Map.ofEntries(Map.entry(970, "Carrots"), Map.entry(314, "Spinach"));
        // Returns an unmodifiable map containing keys and values extracted from the given entries.
        System.out.println("vegetablesMap = " + vegetablesMap);
        // vegetablesMap.put(303,"Tomato"); // UnsupportedOperationException throws at Runtime

        // Java 10
        List<String> countriesImmutableView = List.copyOf(countries);
        System.out.println("countriesImmutableView = " + countriesImmutableView);
        System.out.println("Adding spain to countries Collection");
        countries.add("Spain");
        System.out.println("countries = " + countries);
        System.out.println("countriesImmutableView = " + countriesImmutableView);

        //  static <E> List<E> copyOf(Collection<? extends E> coll)
        //  Returns an unmodifiable List containing the elements of the given Collection, in its iteration order.
        //  The given Collection must not be null, and it must not contain any null elements.
        //  If the given Collection is subsequently modified, the returned List will not reflect such modifications.

        // Unmodifiable Views
        List<String> countriesView = Collections.unmodifiableList(countries);
        System.out.println("countries = " + countries);
        System.out.println("countriesView = " + countriesView);
        System.out.println("Adding USA to the countries collection");
        countries.add("USA");
        System.out.println("countries = " + countries);
        System.out.println("countriesView = " + countriesView);
        //  countriesView.add("Italy"); // UnsupportedOperationException throws at runtime

        System.out.println("Collections.min(countries) = " + Collections.min(countries));
        Collections.fill(countries, null);
        //public static <T> void fill(List<? super T> list, T obj)
        //Replaces all of the elements of the specified list with the specified element
        System.out.println("countries = " + countries); // countries = [null, null, null, null]

    }
}
