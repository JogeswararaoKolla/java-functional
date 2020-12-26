package org.jkolla.recipes;

import org.jkolla.components.UtilFunction;
import org.jkolla.models.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeaderBodyTrailerTypeProcessing {
    public static void main(String[] args) {

        List<TransactionInput> transactionsInput = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(".", "resources", "TransactionInput.txt"))) {
           transactionsInput =
                    lines.map(HeaderBodyTrailerTypeProcessing::getTransactionInput)
                            .collect(Collectors.toUnmodifiableList());

        } catch (IOException e) {
            e.printStackTrace();
        }

       // transactionsInput.set(0,null); all mutating methods add, addAll, remove, replaceAll, set, sort throw UnsupportedOperationException
        transactionsInput.forEach(System.out::println);

        Collector<TransactionInput, RollupTemporaryType, RollupTemporaryType> rollupCollector =
                Collector.of(
                new Supplier<RollupTemporaryType>() {
                    @Override
                    public RollupTemporaryType get() {
                        return new RollupTemporaryType(0, 0, 0.0,null);
                    }
                },
                new BiConsumer<RollupTemporaryType, TransactionInput>() {
                    @Override
                    public void accept(RollupTemporaryType rollupTemporaryType, TransactionInput transactionInput) {
                        rollupTemporaryType.numItems = rollupTemporaryType.numItems + 1;
                        rollupTemporaryType.totalQuantity = rollupTemporaryType.totalQuantity + transactionInput.getQuantity();
                        rollupTemporaryType.totalCost = rollupTemporaryType.totalCost + (transactionInput.getQuantity() * transactionInput.getCost());
                        rollupTemporaryType.customerName = transactionInput.getCustomerName();
                    }
                },
                new BinaryOperator<RollupTemporaryType>() {
                    @Override
                    public RollupTemporaryType apply(RollupTemporaryType rollupTemporaryType1, RollupTemporaryType rollupTemporaryType2) {
                        RollupTemporaryType rollupTemporaryType = new RollupTemporaryType(0,0,0.0,null);

                        rollupTemporaryType.numItems =  rollupTemporaryType1.numItems + rollupTemporaryType2.numItems;
                        rollupTemporaryType.totalQuantity = rollupTemporaryType1.totalQuantity + rollupTemporaryType2.totalQuantity;
                        rollupTemporaryType.totalCost = rollupTemporaryType1.totalCost + rollupTemporaryType2.totalCost;
                        rollupTemporaryType.customerName =  rollupTemporaryType1.customerName;

                        return rollupTemporaryType;
                    }
                }
                ,
                new Function<RollupTemporaryType, RollupTemporaryType>() {
                    @Override
                    public RollupTemporaryType apply(RollupTemporaryType rollupTemporaryType) {
                        RollupTemporaryType rollupTemporaryTypeOutput = new RollupTemporaryType(0,0,0.0,null);

                        rollupTemporaryTypeOutput.numItems = rollupTemporaryType.numItems;
                        rollupTemporaryTypeOutput.totalQuantity = rollupTemporaryType.totalQuantity;
                       // rollupTemporaryTypeOutput.totalCost = rollupTemporaryType.totalCost;
                        rollupTemporaryTypeOutput.totalCost = Math.round(rollupTemporaryType.totalCost*100)/100.0;
                        rollupTemporaryTypeOutput.customerName = rollupTemporaryType.customerName;

                        return rollupTemporaryTypeOutput;
                    }
                }

        );

        Map<Integer, RollupTemporaryType> collect = transactionsInput.stream()
                .parallel()
                .collect(
                        Collectors.groupingBy(
                                TransactionInput::getTransactionId,
                                rollupCollector
                        ));

        collect.forEach((k,v) -> System.out.println(k + " = " + v));

        List<HeaderBodyTrailerInterType> headerAndTrailer = collect.entrySet()
                .stream()
                .flatMap(e -> Stream.of(0, 1).map(
                        counter -> counter == 0 ? new HeaderBodyTrailerInterType(1,e.getKey(),"H", new HeaderType(e.getKey(),e.getValue().customerName,e.getValue().numItems), null, null) :
                                new HeaderBodyTrailerInterType(3,e.getKey(),"T", null, null, new TrailerType(e.getValue().totalQuantity,e.getValue().totalCost))))
                .collect(Collectors.toUnmodifiableList());

      //  headerAndTrailer.forEach(System.out::println);

        List<HeaderBodyTrailerInterType> body = transactionsInput.stream()
                .map(e -> new HeaderBodyTrailerInterType(2, e.getTransactionId(), "B", null, new BodyType(e.getItem(), e.getQuantity(), e.getCost()), null))
                .collect(Collectors.toUnmodifiableList());

      //  body.forEach(System.out::println);
      // We are reading 2 collections streams at the same time using Stream.of and then flatten using flatMap()
        List<HeaderBodyTrailerFinalType> headerBodyAndTrailer = Stream.of(headerAndTrailer.stream(), body.stream())
                .flatMap(Function.identity())
                .sorted(Comparator.comparing(HeaderBodyTrailerInterType::getTransactionId)
                        .thenComparing(HeaderBodyTrailerInterType::getOrdering))
                .peek(System.out::println)
                .map(new Function<HeaderBodyTrailerInterType, HeaderBodyTrailerFinalType>() {
                    @Override
                    public HeaderBodyTrailerFinalType apply(HeaderBodyTrailerInterType headerBodyTrailerInterType) {
                        return new HeaderBodyTrailerFinalType(headerBodyTrailerInterType.getKind(),headerBodyTrailerInterType.getHeader(),headerBodyTrailerInterType.getBody(),headerBodyTrailerInterType.getTrailer());
                    }
                })
                .collect(Collectors.toUnmodifiableList());

       // headerBodyAndTrailer.forEach(System.out::println);

        Path outputFilePath = Paths.get(".","resources/headerBodyTrailerOutput.txt");

        try {
            Files.deleteIfExists(outputFilePath);
            System.out.println("File: " + outputFilePath + " Deleted!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Writing the Collection List<T> data to a File provided path.
        new UtilFunction<HeaderBodyTrailerFinalType>().writeToFile(headerBodyAndTrailer,outputFilePath);

        boolean exists = Files.exists(outputFilePath);
        System.out.println("exists = " + exists);

    }

    private static TransactionInput getTransactionInput(String line) {
        String[] split = line.split(",");
        return new TransactionInput(Integer.parseInt(split[0]), split[1], split[2], Integer.parseInt(split[3]), Double.parseDouble(split[4]));
    }
}

// parallel()
//334567 = RollupTemporaryType{numItems=3, totalQuantity=5, totalCost=2.25, customerName='Joe'}
//334566 = RollupTemporaryType{numItems=2, totalQuantity=2, totalCost=8.3, customerName='Veronica'}
//334573 = RollupTemporaryType{numItems=1, totalQuantity=3, totalCost=1.05, customerName='Joe'}
//334572 = RollupTemporaryType{numItems=2, totalQuantity=3, totalCost=5.89, customerName='Mary'}
//334568 = RollupTemporaryType{numItems=1, totalQuantity=2, totalCost=11.98, customerName='Mary'}
//334571 = RollupTemporaryType{numItems=3, totalQuantity=6, totalCost=12.54, customerName='Koko'}
//334570 = RollupTemporaryType{numItems=5, totalQuantity=13, totalCost=35.01, customerName='Susan'}

//HeaderBodyTrailerInterType{ordering=1, transactionId=334566, kind='H', header=HeaderType{transactionId=334566, customerName='Veronica', numItems=2}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334566, kind='B', header=null, body=BodyType{item='apple', quantity=1, cost=0.45}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334566, kind='B', header=null, body=BodyType{item='roast', quantity=1, cost=7.85}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334566, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=2, totalCost=8.3}}
//HeaderBodyTrailerInterType{ordering=1, transactionId=334567, kind='H', header=HeaderType{transactionId=334567, customerName='Joe', numItems=3}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334567, kind='B', header=null, body=BodyType{item='apple', quantity=1, cost=0.45}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334567, kind='B', header=null, body=BodyType{item='orange', quantity=2, cost=0.55}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334567, kind='B', header=null, body=BodyType{item='tomato', quantity=2, cost=0.35}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334567, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=5, totalCost=2.25}}
//HeaderBodyTrailerInterType{ordering=1, transactionId=334568, kind='H', header=HeaderType{transactionId=334568, customerName='Mary', numItems=1}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334568, kind='B', header=null, body=BodyType{item='chicken', quantity=2, cost=5.99}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334568, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=2, totalCost=11.98}}
//HeaderBodyTrailerInterType{ordering=1, transactionId=334570, kind='H', header=HeaderType{transactionId=334570, customerName='Susan', numItems=5}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334570, kind='B', header=null, body=BodyType{item='orange', quantity=5, cost=0.55}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334570, kind='B', header=null, body=BodyType{item='lettuce', quantity=1, cost=0.95}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334570, kind='B', header=null, body=BodyType{item='milk', quantity=4, cost=4.99}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334570, kind='B', header=null, body=BodyType{item='roast', quantity=1, cost=6.25}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334570, kind='B', header=null, body=BodyType{item='bread', quantity=2, cost=2.55}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334570, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=13, totalCost=35.01}}
//HeaderBodyTrailerInterType{ordering=1, transactionId=334571, kind='H', header=HeaderType{transactionId=334571, customerName='Koko', numItems=3}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334571, kind='B', header=null, body=BodyType{item='chicken', quantity=2, cost=5.27}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334571, kind='B', header=null, body=BodyType{item='orange', quantity=3, cost=0.55}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334571, kind='B', header=null, body=BodyType{item='tomato', quantity=1, cost=0.35}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334571, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=6, totalCost=12.54}}
//HeaderBodyTrailerInterType{ordering=1, transactionId=334572, kind='H', header=HeaderType{transactionId=334572, customerName='Mary', numItems=2}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334572, kind='B', header=null, body=BodyType{item='apple', quantity=2, cost=0.45}, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334572, kind='B', header=null, body=BodyType{item='milk', quantity=1, cost=4.99}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334572, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=3, totalCost=5.89}}
//HeaderBodyTrailerInterType{ordering=1, transactionId=334573, kind='H', header=HeaderType{transactionId=334573, customerName='Joe', numItems=1}, body=null, trailer=null}
//HeaderBodyTrailerInterType{ordering=2, transactionId=334573, kind='B', header=null, body=BodyType{item='tomato', quantity=3, cost=0.35}, trailer=null}
//HeaderBodyTrailerInterType{ordering=3, transactionId=334573, kind='T', header=null, body=null, trailer=TrailerType{totalQuantity=3, totalCost=1.05}}
