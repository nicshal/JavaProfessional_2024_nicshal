package ru.nicshal.advanced.homework.atm;

import java.util.List;

public interface ATM {

    void loadATM(List<Banknote> pack);

    List<Banknote> getBanknotes(int amount);

    int getBalance();

}
