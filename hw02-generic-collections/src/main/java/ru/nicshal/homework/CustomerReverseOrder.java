package ru.nicshal.homework;

import java.util.*;

public class CustomerReverseOrder {

    private final Deque<Customer> deque = new ArrayDeque<>();

    public void add(Customer customer) {
        deque.add(customer);
    }

    public Customer take() {
        return deque.pollLast();
    }
}
