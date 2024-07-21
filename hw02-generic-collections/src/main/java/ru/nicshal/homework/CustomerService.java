package ru.nicshal.homework;

import java.util.*;

public class CustomerService {

    TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> item = map.firstEntry();
        return Map.entry(new Customer(item.getKey()), item.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> item = map.higherEntry(customer);
        if (item != null) {
            return Map.entry(new Customer(item.getKey()), item.getValue());
        }
        return null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
