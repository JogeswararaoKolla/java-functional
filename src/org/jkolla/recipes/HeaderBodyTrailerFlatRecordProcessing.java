package org.jkolla.recipes;

import org.jkolla.models.HeaderBodyTrailerFlatRecordType;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeaderBodyTrailerFlatRecordProcessing {
    public static void main(String[] args) {
        
        Path path = Paths.get(".","resources/headerBodyTrailerOutput.txt");

        // if any errors in parsing the input string here an empty stream is returned otherwise stream with one element
        Function<String, Stream<HeaderBodyTrailerFlatRecordType>> lineToFlatRecordStream =
                new Function<String, Stream<HeaderBodyTrailerFlatRecordType>>() {
                    @Override
                    public Stream<HeaderBodyTrailerFlatRecordType> apply(String line) {

                        String[] attributes = line.split(",");
                        HeaderBodyTrailerFlatRecordType headerBodyTrailerFlatRecordType = null;
                        try {
                            if (attributes[0].equals("H"))
                                headerBodyTrailerFlatRecordType = new HeaderBodyTrailerFlatRecordType("H", Integer.parseInt(attributes[1]), attributes[2], Integer.parseInt(attributes[3]), null, null, null, null, null);
                            else if (attributes[0].equals("B"))
                                headerBodyTrailerFlatRecordType = new HeaderBodyTrailerFlatRecordType("B", null, null, null, attributes[1], Integer.parseInt(attributes[2]), Double.parseDouble(attributes[3]), null, null);
                            else if (attributes[0].equals("T"))
                                headerBodyTrailerFlatRecordType = new HeaderBodyTrailerFlatRecordType("T", null, null, null, null, null, null, Integer.parseInt(attributes[1]), Double.parseDouble(attributes[2]));
                            return Stream.of(headerBodyTrailerFlatRecordType);
                        } catch (Exception e) {
                        }
                        return Stream.empty();
                    }
                };

        try (Stream<String> lines = Files.lines(path)) {

            List<HeaderBodyTrailerFlatRecordType> flatRecordsColl = lines.flatMap(e -> lineToFlatRecordStream.apply(e))
                    .collect(Collectors.toList());
            System.out.println("flatRecordsColl.size() = " + flatRecordsColl.size()); // flatRecordsColl.size() = 31

            flatRecordsColl.stream().limit(3).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Here the errors in input string is handled using the attributes length and try catch block, returns null in case of error for a record.
        Function<String, HeaderBodyTrailerFlatRecordType> lineToFlatRecord =
                new Function<String, HeaderBodyTrailerFlatRecordType>() {
                    @Override
                    public HeaderBodyTrailerFlatRecordType apply(String line) {

                        String[] attributes = line.split(",");
                        HeaderBodyTrailerFlatRecordType headerBodyTrailerFlatRecordType = null;
                        try {
                            if (attributes[0].equals("H") && attributes.length == 4)
                                headerBodyTrailerFlatRecordType = new HeaderBodyTrailerFlatRecordType("H", Integer.parseInt(attributes[1]), attributes[2], Integer.parseInt(attributes[3]), null, null, null, null, null);
                            else if (attributes[0].equals("B") && attributes.length == 4)
                                headerBodyTrailerFlatRecordType = new HeaderBodyTrailerFlatRecordType("B", null, null, null, attributes[1], Integer.parseInt(attributes[2]), Double.parseDouble(attributes[3]), null, null);
                            else if (attributes[0].equals("T") && attributes.length == 3)
                                headerBodyTrailerFlatRecordType = new HeaderBodyTrailerFlatRecordType("T", null, null, null, null, null, null, Integer.parseInt(attributes[1]), Double.parseDouble(attributes[2]));
                        } catch (Exception e) {
                        }
                        return headerBodyTrailerFlatRecordType;
                    }
                };
        
       try(BufferedReader reader = Files.newBufferedReader(path);) {
           final Stream<String> lines = reader.lines();

           List<HeaderBodyTrailerFlatRecordType> collect = lines
                   .map(e -> lineToFlatRecord.apply(e))
                   .filter(Objects::nonNull) // Filtering the null objects returned from above mapping phase
                   .collect(Collectors.toList());
           System.out.println("collect.size() = " + collect.size()); // collect.size() = 32

       } catch (IOException e) {
           e.printStackTrace();
       }

//HeaderBodyTrailerFlatRecordType{kind='H', transactionId=334566, customerName='Veronica', numItems=2, item='null', quantity=null, cost=null, totalQuantity=null, totalCost=null}
//HeaderBodyTrailerFlatRecordType{kind='B', transactionId=null, customerName='null', numItems=null, item='apple', quantity=1, cost=0.45, totalQuantity=null, totalCost=null}
//HeaderBodyTrailerFlatRecordType{kind='B', transactionId=null, customerName='null', numItems=null, item='roast', quantity=1, cost=7.85, totalQuantity=null, totalCost=null}

    }
}
