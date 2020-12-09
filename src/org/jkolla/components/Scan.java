package org.jkolla.components;

import org.jkolla.models.TransactionEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scan {
    public static void main(String[] args) throws IOException {

        Path filepath = Paths.get(".","resources","events.dat");
        Stream<String> lines = Files.lines(filepath);

        List<TransactionEvent> transactionEvents = lines.map(e -> {
            String[] elements = e.split(",");
            return new TransactionEvent(LocalDate.parse(elements[0]), elements[1], Double.parseDouble(elements[2]));
        }).collect(Collectors.toList());

        System.out.println("transactionEvents = " + transactionEvents);

        Collector<TransactionEvent, ScanTemporaryTypeWrapper, List<ScanTemporaryType>> scanCollector = Collector.of(new Supplier<ScanTemporaryTypeWrapper>() {
            @Override
            public ScanTemporaryTypeWrapper get() {
               //  System.out.println("Calling Supplier<A> supplier()");
                return new ScanTemporaryTypeWrapper(0, new ArrayList<>());
            }
        }, new BiConsumer<ScanTemporaryTypeWrapper, TransactionEvent>() {
            @Override
            public void accept(ScanTemporaryTypeWrapper scanTemporaryTypeWrapper, TransactionEvent transactionEvent) {
                // System.out.println("Calling BiConsumer<A,T> accumulator()");
                scanTemporaryTypeWrapper.counter = scanTemporaryTypeWrapper.counter + 1;
                ScanTemporaryType scanTemporaryType = new ScanTemporaryType(transactionEvent.getDt(), transactionEvent.getKind(), transactionEvent.getAmount(), scanTemporaryTypeWrapper.counter);
               // System.out.println("scanTemporaryType = " + scanTemporaryType);
                scanTemporaryTypeWrapper.scanTemporaryTypes.add(scanTemporaryType);
            }
        }, new BinaryOperator<ScanTemporaryTypeWrapper>() {
            @Override
            public ScanTemporaryTypeWrapper apply(ScanTemporaryTypeWrapper scanTemporaryTypeWrapper, ScanTemporaryTypeWrapper scanTemporaryTypeWrapper2) {
                return null;
            }
        }, new Function<ScanTemporaryTypeWrapper, List<ScanTemporaryType>>() {
            @Override
            public List<ScanTemporaryType> apply(ScanTemporaryTypeWrapper scanTemporaryTypeWrapper) {
               // System.out.println("Calling Function<A,R> finisher()");
                return scanTemporaryTypeWrapper.getScanTemporaryTypes();
            }
        });

        // generate Scan keys grouping by date
        Map<LocalDate, List<ScanTemporaryType>> collect = transactionEvents.stream()
                .collect(Collectors.groupingBy(e -> e.getDt(),
                        scanCollector));
        System.out.println("collect = " + collect);

        // Here collect Map value is List<ScanTemporaryType> so if we do map then we get Stream<List<ScanTemporaryType>>
        // Other case if we use map on value.stream() then we get Stream<Stream<ScanTemporaryType>>
        // so we need to use flatMap to convert Stream<Stream<ScanTemporaryType> to Stream<ScanTemporaryType>
        Stream<Stream<ScanTemporaryType>> streamStream = collect.entrySet().stream()
                .peek(System.out::println) // This never executes as no terminal operation.
                .map(e -> e.getValue().stream());

        System.out.println("streamStream = " + streamStream); // streamStream = java.util.stream.ReferencePipeline$3@2e0fa5d3
        System.out.println("generate Scan keys grouping by date");
        collect.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .forEach(System.out::println);

       // generate Scan keys grouping by empty key
        System.out.println();
        List<ScanTemporaryType> collect1 = transactionEvents.stream()
                .collect(scanCollector);
        System.out.println("generate Scan keys grouping by empty key");
        collect1.forEach(System.out::println);

        System.out.println();
        // generate Scan keys grouping by dt and kind
        Map<LocalDate, Map<String, List<ScanTemporaryType>>> collect2 = transactionEvents.stream()
                .sorted(Comparator.comparing(TransactionEvent::getDt)
                        .thenComparing(TransactionEvent::getKind))
                .collect(Collectors.groupingBy(e -> e.getDt(),
                        Collectors.groupingBy(p -> p.getKind(),
                                scanCollector)));

        System.out.println("collect2 = " + collect2);

        System.out.println("generate Scan keys grouping by dt and kind");
        collect2.forEach((k,v) -> {
            System.out.println(k + " = " + v);
        });

        collect2.entrySet()
                .stream()
                .flatMap(e -> e.getValue().entrySet().stream())
                .flatMap(p -> p.getValue().stream())
        .forEach(System.out::println);
         // flatMap(e -> e.getValue().entrySet().stream().flatMap((p ->p.getValue().stream())))
         // entrySet();  Set<Map.Entry<LocalDate, Map<String, List<ScanTemporaryType>>>>
         // entrySet().stream(); Stream<Map.Entry<LocalDate, Map<String, List<ScanTemporaryType>>>>
         // flatMap; Stream<Map.Entry<String, List<ScanTemporaryType>>>
         // flatMap; Stream<ScanTemporaryType>

    }
}
