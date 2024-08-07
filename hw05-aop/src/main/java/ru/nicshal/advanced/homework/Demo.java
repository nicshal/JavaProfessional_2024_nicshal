package ru.nicshal.advanced.homework;

public class Demo {

    public static void main(String[] args) {
        TestLoggingInterface myClass = Ioc.createLogging(TestLoggingInterface.class, new TestLogging());
        myClass.calculation(1);
        myClass.calculation(1, 7);
        myClass.calculation(1, 887, "TEST");
    }

}
