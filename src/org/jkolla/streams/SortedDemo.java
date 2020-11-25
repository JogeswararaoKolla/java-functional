package org.jkolla.streams;

import org.jkolla.models.Customer;
import org.jkolla.utils.Util;

import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.*;

class CustomerMapper {
    String state;
    Integer customerId;
    String customerName;

    public CustomerMapper(String state, Integer customerId, String customerName) {
        this.state = state;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "CustomerMapper{" +
                "state='" + state + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
public class SortedDemo {
    public static void main(String[] args) {

        List<Customer> customers = new Util().createCustomers();

        System.out.println(customers.size());

//  Stream<T>	sorted(Comparator<? super T> comparator)
//  Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator.

        List<CustomerMapper> collect = customers.stream()
                .sorted(Comparator.comparing(Customer::getState).thenComparing(Customer::getCustomerId))
                .map(e -> new CustomerMapper(e.getState(), e.getCustomerId(), e.getFirstName()))
                .collect(toList());

        collect.forEach(System.out::println);

    }
}
