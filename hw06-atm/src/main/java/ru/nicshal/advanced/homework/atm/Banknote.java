package ru.nicshal.advanced.homework.atm;

public class Banknote {

    private final BanknoteFaceValue faceValue;
    private final String currency;

    public Banknote(BanknoteFaceValue faceValue, String currency) {
        this.faceValue = faceValue;
        this.currency = currency;
    }

    public BanknoteFaceValue getFaceValue() {
        return faceValue;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "faceValue=" + faceValue +
                ", currency='" + currency + '\'' +
                '}';
    }

}
