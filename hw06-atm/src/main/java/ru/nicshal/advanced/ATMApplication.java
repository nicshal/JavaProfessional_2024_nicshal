package ru.nicshal.advanced;

import ru.nicshal.advanced.homework.atm.ATM;
import ru.nicshal.advanced.homework.atm.Banknote;
import ru.nicshal.advanced.homework.atm.Cassette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static ru.nicshal.advanced.homework.atm.BanknoteFaceValue.*;

public class ATMApplication {

    private static final Logger logger = LoggerFactory.getLogger(ATMApplication.class);

    public static void main(String[] args) {
        Cassette testCassette = new Cassette(1, new ArrayList<>());
        System.out.println(testCassette);
        List<Banknote> lst = List.of(
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"),
                new Banknote(RUB500, "RUB"));
        testCassette.addBanknotes(lst);
        logger.info("Загружено в кассету ---> {}", testCassette);
        logger.info("Баланс кассеты ---> {}", testCassette.getBalance());
        logger.info("Забираем купюры ---> {}", testCassette.removeBanknoteCount(2));
        logger.info("Баланс кассеты ---> {}", testCassette.getBalance());

        ATM atm = new ATM("Тестовый");
        List<Banknote> lst2 = List.of(
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

        atm.loadATM(lst2);
        System.out.println(atm);
        logger.info("Содержимое АТМ ---> {}", atm);
        logger.info("Баланс АТМ ---> {}", atm.getBalance());
        System.out.println(atm.getBanknotes(5650));
        logger.info("Выдача пачки денег ---> {}", atm.getBanknotes(5650));
        logger.info("Баланс АТМ ---> {}", atm.getBalance());
    }

}
