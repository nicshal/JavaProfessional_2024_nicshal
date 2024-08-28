package ru.nicshal.advanced.homework.atm;

import java.util.ArrayList;
import java.util.List;

public class RubleATM implements ATM {

    private final String ATMID;
    private final List<Cassette> cassettes;

    public RubleATM(String ATMID, List<Cassette> cassettes) {
        this.ATMID = ATMID;
        this.cassettes = cassettes;
    }

    public String getATMID() {
        return ATMID;
    }

    public void loadATM(List<Banknote> pack) {
        for (Banknote banknote : pack) {
            cassettes.get(banknote.getFaceValue().ordinal()).addBanknote(banknote);
        }
    }

    public List<Banknote> getBanknotes(int amount) {
        if (amount > getBalance()) {
            throw new BalanceException("Невозможно выдать всю сумму целиком. В банкомате не хватает средств");
        }

        List<Banknote> pack = new ArrayList<>();
        int banknoteInAmont;
        int banknoteCount;
        int balance = amount;
        for (Cassette cassette : cassettes.reversed()) {
            banknoteInAmont = balance / BanknoteFaceValue.values()[cassette.getPosition()].getDenomination();
            if (banknoteInAmont > 0 && cassette.getBalance() > 0) {
                banknoteCount = Math.min(banknoteInAmont, cassette.getBanknoteCount());
                pack.addAll(cassette.removeBanknoteCount(banknoteCount));
                balance -= BanknoteFaceValue.values()[cassette.getPosition()].getDenomination() * banknoteCount;
            }
        }

        if (balance != 0) {
            throw new BalanceException("Выдача невозможна. Отсутствуют подходящие купюры для обспечения выдачи всей суммы");
        }
        return pack;
    }

    public int getBalance() {
        return cassettes.stream().map(Cassette::getBalance).reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "ATM{" +
                "ATMID='" + ATMID + '\'' +
                ", cassettes=" + cassettes +
                '}';
    }

}
