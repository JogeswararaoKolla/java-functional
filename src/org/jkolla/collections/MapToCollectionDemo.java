package org.jkolla.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapToCollectionDemo {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>(){{
            put(850,"Broccoli");
            put(970,"Carrots");
            put(314,"Spinach");
        }};

        System.out.println("map = " + map);
        
        record Product(Integer productId ,String productName){
            Product(){
                this(999,"default");
            }
        }

        Set<Product> collect = map.entrySet()
                .stream()
                .map(new Function<Map.Entry<Integer, String>, Product>() {
                    @Override
                    public Product apply(Map.Entry<Integer, String> integerStringEntry) {
                        return new Product(integerStringEntry.getKey(), integerStringEntry.getValue());
                    }
                })
                .collect(Collectors.toCollection(() ->  new HashSet<>()));

        System.out.println("collect = " + collect); // collect = [Product[productId=970, productName=Carrots], Product[productId=314, productName=Spinach], Product[productId=850, productName=Broccoli]]


    }
}
