package ru.nicshal.advanced.homework13.services;

public interface IOService {
    void out(String message);

    String readLn(String prompt);

    int readInt(String prompt);
}
