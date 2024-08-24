package ru.nicshal.advanced.homework.atm;

import java.util.ArrayList;
import java.util.List;

public class Cassette {

    private final int position;
    private final List<Banknote> banknotes;
    //TODO - можно еще объем кассеты заложить

    public Cassette(int position, List<Banknote> banknotes) {
        this.position = position;
        this.banknotes = banknotes;
    }

    public int getPosition() {
        return position;
    }

    public void addBanknotes(List<Banknote> pack) {
        banknotes.addAll(pack);
    }

    public void addBanknote(Banknote banknote) {
        banknotes.add(banknote);
    }

    public List<Banknote> removeBanknoteCount(int count) {
        List<Banknote> pack = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            pack.add(banknotes.removeFirst());
        }
        return pack;
    }

    public int getBalance() {
        return banknotes.stream().map(item -> item.getFaceValue().getDenomination()).reduce(0, Integer::sum);
    }

    public int getBanknoteCount() {
        return banknotes.size();
    }

    @Override
    public String toString() {
        return "Cassette{" +
                "position=" + position +
                ", banknotes=" + banknotes +
                '}';
    }

}
