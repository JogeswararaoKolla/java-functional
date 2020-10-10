package org.jkolla.collections;

import org.jkolla.models.FilterOption;
import org.jkolla.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ListToMapDemo {
    public static void main(String[] args) {
        Util  util = new Util();
        List<FilterOption> filterOptions = util.createFilterOptions();
        filterOptions.add(null);
        System.out.println("filterOptions = " + filterOptions);
        Map<String, Set<String>> filterOptionsMap = filterOptions.stream()
                .filter(Objects::nonNull) // Filters null object
                .filter(option -> option.getCategory() != null) // Filters FilterOption{category='null', selectValues=null}
                .peek(System.out::println)
                .collect(Collectors.toMap(
                        FilterOption::getCategory,
                        e -> e.getSelectValues().stream()
                                .filter(Objects::nonNull) // Filters the null objects
                                .filter(p -> !p.isEmpty() && p != "null" && p != "empty") // Filters "","null" and "empty" strings
                                //.collect(Collectors.toList()) // allows duplicates filterOptionsMap = {980=[900, 900], 936=[475, 877, 475], 947=[189]}
                                .collect(Collectors.toSet()) // don't allow duplicates filterOptionsMap = {980=[900], 936=[475, 877], 947=[189]}
                ));
        System.out.println("filterOptionsMap = " + filterOptionsMap);
    }
}
