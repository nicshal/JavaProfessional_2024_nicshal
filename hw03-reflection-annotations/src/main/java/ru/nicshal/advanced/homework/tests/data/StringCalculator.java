package ru.nicshal.advanced.homework.tests.data;

public class StringCalculator {

    private String arg1;
    private String arg2;

    public StringCalculator(String arg1, String arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String add() {
        return arg1 + "->" + arg2;
    }

    public String sub() {
        if (arg1.endsWith(arg2)) {
            return arg1.substring(0, arg1.length() - arg2.length());
        } else {
            return arg1;
        }
    }

    public String mul() {
        return arg1.repeat(arg2.length());
    }

    @Override
    public String toString() {
        return "StringCalculator{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }

}
