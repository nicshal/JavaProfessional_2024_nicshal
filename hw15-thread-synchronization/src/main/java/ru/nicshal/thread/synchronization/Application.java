package ru.nicshal.thread.synchronization;

public class Application {

    public static void main(String[] args) {
        final Runnable printer = new Printer();

        new Thread(printer, "Thread_1").start();
        new Thread(printer, "Thread_2").start();
    }

}
