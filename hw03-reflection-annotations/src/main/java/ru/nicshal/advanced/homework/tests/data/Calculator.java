package ru.nicshal.advanced.homework.tests.data;

public class Calculator {

    private int arg1;
    private int arg2;

    public Calculator(int arg1, int arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public int add() {
        return arg1 + arg2;
    }

    public int sub() {
        return arg1 - arg2;
    }

    public int mul() {
        return arg1 * arg2;
    }

    public int div() {
        return arg1 / arg2;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }

}
