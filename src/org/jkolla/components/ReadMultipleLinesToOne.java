package org.jkolla.components;

import org.jkolla.models.CustomerTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ReadMultipleLinesToOne {
    public static void main(String[] args) {
        Path path = Paths.get(".","resources","TransactionsData.txt");

        try(Stream<String> lines = Files.lines(path);) {
            Spliterator<String> lineSpliterator = lines.spliterator(); // Extract the spliterator() from stream
            Spliterator<CustomerTransaction> transactionSpliterator =  new TransactionSpliterator(lineSpliterator);
            Stream<CustomerTransaction> transactions = StreamSupport.stream(transactionSpliterator, false);
            transactions.forEach(System.out::println);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        // CustomerTransaction{customerId='C008231', transDate=1993-10-22, amount=122.0}
        // CustomerTransaction{customerId='C002142', transDate=1994-06-22, amount=22.25}
        // CustomerTransaction{customerId='C003213', transDate=1993-02-12, amount=47.95}
    }
}
