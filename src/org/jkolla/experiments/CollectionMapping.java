package org.jkolla.experiments;

import org.jkolla.components.UtilFunction;
import org.jkolla.utils.Util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CollectionMapping {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("this","is","list","of","strings");
        Function<String,String> function = s -> s + "-->" + s.length();

        System.out.println("stringsMapping = " + new UtilFunction<String>().getCollMap(strings,function));
        // stringsMapping = [this-->4, is-->2, list-->4, of-->2, strings-->7]
        System.out.println("stringsMapping = " + new UtilFunction<String>().getCollMap(strings,s -> s.length()));
        // stringsMapping = [4, 2, 4, 2, 7]
    }
}
