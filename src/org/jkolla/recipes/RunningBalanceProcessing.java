package org.jkolla.recipes;

import org.jkolla.models.TransactionInput;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

public class RunningBalanceProcessing {
    public static void main(String[] args) {

        List<TransactionInput> transactionInputs = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(".", "resources", "TransactionInput.txt"));) {
            transactionInputs=  lines.map(e -> {
                       String[] split = e.split(",");
                    return  new TransactionInput(Integer.parseInt(split[0]),split[1],split[2],Integer.parseInt(split[3]),Double.parseDouble(split[4]));
                   }).collect(toUnmodifiableList());

        } catch (IOException e) {
            e.printStackTrace();
        }

      transactionInputs.forEach(System.out::println);

        Collector<TransactionInput, RunningBalanceScanTemporaryTypeWrapper, List<RunningBalanceScanTemporaryType>> scanCollector = Collector.of(
                new Supplier<RunningBalanceScanTemporaryTypeWrapper>() {
                    @Override
                    public RunningBalanceScanTemporaryTypeWrapper get() {
                        return new RunningBalanceScanTemporaryTypeWrapper(0.0, new ArrayList<>());
                    }
                },
                new BiConsumer<RunningBalanceScanTemporaryTypeWrapper, TransactionInput>() {
                    @Override
                    public void accept(RunningBalanceScanTemporaryTypeWrapper runningBalanceScanTemporaryTypeWrapper, TransactionInput transactionInput) {
                        DecimalFormat df = new DecimalFormat("#.##");
                        runningBalanceScanTemporaryTypeWrapper.runningBal =  runningBalanceScanTemporaryTypeWrapper.runningBal + transactionInput.getCost();
                        RunningBalanceScanTemporaryType runningBalanceScanTemporaryType = new RunningBalanceScanTemporaryType(transactionInput.getTransactionId(),transactionInput.getCustomerName(),transactionInput.getItem(),transactionInput.getQuantity(),transactionInput.getCost(),runningBalanceScanTemporaryTypeWrapper.runningBal);
                      //  RunningBalanceScanTemporaryType runningBalanceScanTemporaryType = new RunningBalanceScanTemporaryType(transactionInput.getTransactionId(),transactionInput.getCustomerName(),transactionInput.getItem(),transactionInput.getQuantity(),transactionInput.getCost(),Double.parseDouble(df.format(runningBalanceScanTemporaryTypeWrapper.runningBal*100.0/100.0)));
                        runningBalanceScanTemporaryTypeWrapper.runningBalanceScanTemporaryTypes.add(runningBalanceScanTemporaryType);
                    }
                },
                new BinaryOperator<RunningBalanceScanTemporaryTypeWrapper>() {
                    @Override
                    public RunningBalanceScanTemporaryTypeWrapper apply(RunningBalanceScanTemporaryTypeWrapper runningBalanceScanTemporaryTypeWrapper1, RunningBalanceScanTemporaryTypeWrapper runningBalanceScanTemporaryTypeWrapper2) {
                        return null;
                    }
                },
                new Function<RunningBalanceScanTemporaryTypeWrapper, List<RunningBalanceScanTemporaryType>>() {
                    @Override
                    public List<RunningBalanceScanTemporaryType> apply(RunningBalanceScanTemporaryTypeWrapper runningBalanceScanTemporaryTypeWrapper) {
                        return runningBalanceScanTemporaryTypeWrapper.getRunningBalanceScanTemporaryTypes();
                    }
                }

        );

        Map<Integer, List<RunningBalanceScanTemporaryType>> collect = transactionInputs.stream()
                .collect(groupingBy(
                        TransactionInput::getTransactionId,
                        scanCollector
                ));

        List<RunningBalanceScanTemporaryType> collect1 = collect.entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream())
                .peek(System.out::println)
                .collect(Collectors.toList());


    try( BufferedWriter writer = Files.newBufferedWriter(Paths.get(".", "resources", "RunningBalancesOutput.txt"));) {
            collect1.forEach(s -> {
                try {
                    writer.write(s.getTransactionId() + "," + s.getCustomerName() + ","
                    + s.getItem() + "," + s.getQuantity() + "," + s.getCost() + "," + s.getRunningBalance());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
          writer.close();;
       } catch (IOException e) {
           e.printStackTrace();
       }

    }
}
