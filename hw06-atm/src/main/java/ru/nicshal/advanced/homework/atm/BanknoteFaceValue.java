package ru.nicshal.advanced.homework.atm;

public enum BanknoteFaceValue {

    RUB50(50), RUB100(100), RUB200(200), RUB500(500), RUB1000(1000), RUB2000(2000), RUB5000(5000);

    private BanknoteFaceValue(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    private final int denomination;

}
