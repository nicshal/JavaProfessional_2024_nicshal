package ru.nicshal.homework;

import java.util.*;

public class CustomerReverseOrder {

    Deque<Customer> deque = new ArrayDeque<>();

    public void add(Customer customer) {
        deque.add(customer);
    }

    public Customer take() {
        return deque.pollLast();
    }
}
