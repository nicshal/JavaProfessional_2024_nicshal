package ru.nicshal.homework;

import java.util.ArrayList;
import java.util.List;

public class Summator {
    //TODO - Integer заменил на int. Прирост производительности на 10%
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;
    private final List<Data> listValues = new ArrayList<>();
    private int sumIter = 0;

    // !!! сигнатуру метода менять нельзя
    public void calc(Data data) {
        listValues.add(data);
        if (listValues.size() % 100_000 == 0) {
            listValues.clear();
            sumIter = 0;
        }
        sum += data.getValue();
        sumIter += listValues.size() == 0 ? 0 : data.getValue();

        sumLastThreeValues = data.getValue() + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = data.getValue();

        int sumValues = sumIter;
        //TODO - первоначальный код закомментирован. Для получения значения sumValues исполькован другой алгоритм
        // Прирост производительности в 200 раз
        /*int sumValues = 0;
        for (Data listValue : listValues) {
            sumValues += listValue.getValue();
        }*/

        for (int idx = 0; idx < 3; idx++) {
            someValue += (sumLastThreeValues * sumLastThreeValues / (data.getValue() + 1) - sum);
            someValue = Math.abs(someValue) + listValues.size() + sumValues;
        }
    }

    public Integer getSum() {
        return sum;
    }

    public Integer getPrevValue() {
        return prevValue;
    }

    public Integer getPrevPrevValue() {
        return prevPrevValue;
    }

    public Integer getSumLastThreeValues() {
        return sumLastThreeValues;
    }

    public Integer getSomeValue() {
        return someValue;
    }
}
