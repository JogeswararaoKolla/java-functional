package org.jkolla.components;

import org.jkolla.models.StoreInfo;
import org.jkolla.utils.Util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Normalize {
    public static void main(String[] args) {

        Set<Integer> counter1 = Stream.iterate(0, e -> e + 1)
                .limit(3)
                .collect(Collectors.toSet());
        System.out.println("counter1 = " + counter1);

        List<Integer> counter2 = IntStream.rangeClosed(0, 2)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("counter2 = " + counter2);

        List<StoreInfo> storesWithMultipleManagers = new Util().createStoreInfo();

        System.out.println("storesWithMultipleManagers = " + storesWithMultipleManagers);

        List<NormalizeTemporaryType> storeManagers = storesWithMultipleManagers.stream()
                .flatMap(e -> counter1.stream().map(
                        counter ->
                                counter == 0 ? new NormalizeTemporaryType(e.getStoreNo(), e.getStoreManager1().trim(), counter + 1) :
                                        counter == 1 ? new NormalizeTemporaryType(e.getStoreNo(), e.getStoreManager2().trim(), counter + 1) :
                                                counter == 2 ? new NormalizeTemporaryType(e.getStoreNo(), e.getStoreManager3().trim(), counter + 1) :
                                                        new NormalizeTemporaryType()))
                .filter(select -> !select.storeManagerName.isBlank())
                .collect(Collectors.toList());

        storeManagers.stream()
                .limit(3)
                .forEach(System.out::println);
        /* Result :
           StoreInfoManager{storeNo=1101, storeManagerName='JEFFERY SCHAUBERT', storeManagerNo=1}
           StoreInfoManager{storeNo=1102, storeManagerName='MARY ZARATE', storeManagerNo=1}
           StoreInfoManager{storeNo=1102, storeManagerName='AGUSTIN COTTON', storeManagerNo=2}
        */

    }
}
