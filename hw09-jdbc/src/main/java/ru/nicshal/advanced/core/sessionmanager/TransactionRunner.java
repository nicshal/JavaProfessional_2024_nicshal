package ru.nicshal.advanced.core.sessionmanager;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);

}
