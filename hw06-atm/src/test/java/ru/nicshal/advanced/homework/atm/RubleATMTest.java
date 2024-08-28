package ru.nicshal.advanced.homework.atm;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.nicshal.advanced.homework.atm.BanknoteFaceValue.*;
import static ru.nicshal.advanced.homework.atm.BanknoteFaceValue.RUB50;

public class RubleATMTest {

    private RubleATM rubleAtm;

    @BeforeEach
    public void init() {
        ArrayList<Cassette> cassettes = new ArrayList<>();
        for (int i = 0; i < BanknoteFaceValue.values().length; i++) {
            cassettes.add(new Cassette(i, new ArrayList<>()));
        }
        rubleAtm = new RubleATM("Тестовый", cassettes);
        List<Banknote> banknoteList = List.of(
                new Banknote(RUB5000, "RUB"),
                new Banknote(RUB5000, "RUB"),
                new Banknote(RUB2000, "RUB"),
                new Banknote(RUB2000, "RUB"),
                new Banknote(RUB2000, "RUB"),
                new Banknote(RUB1000, "RUB"),
                new Banknote(RUB1000, "RUB"),
                new Banknote(RUB1000, "RUB"),
                new Banknote(RUB1000, "RUB"),
                new Banknote(RUB1000, "RUB"),
                new Banknote(RUB1000, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB200, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB100, "RUB"),
                new Banknote(RUB50, "RUB"),
                new Banknote(RUB50, "RUB"),
                new Banknote(RUB50, "RUB"),
                new Banknote(RUB50, "RUB"),
                new Banknote(RUB50, "RUB"));
        rubleAtm.loadATM(banknoteList);
    }

    @Test
    @DisplayName("Проверяем загрузку банкомата")
    void loadATMTest() {

        int balance = 26950;
        assertThat(rubleAtm.getBalance()).isEqualTo(balance);
    }

    @Test
    @DisplayName("Проверяем выдачу денег")
    void getBanknotesTest() {

        int testAmount = 5650;
        List<Banknote> banknoteList = rubleAtm.getBanknotes(testAmount);
        int balance = 21300;
        assertThat(rubleAtm.getBalance()).isEqualTo(balance);
        assertThat(banknoteList
                .stream()
                .map(item -> item.getFaceValue().getDenomination())
                .reduce(0, Integer::sum)).isEqualTo(testAmount);
    }

}
