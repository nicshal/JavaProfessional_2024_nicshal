package ru.nicshal.thread.synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

public class Printer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Printer.class);

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 10;
    private String previousThread = "Thread_2";

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            iterate(rangeClosed(MIN_NUMBER, MAX_NUMBER));
            sleep(1000);
            iterate(range(MIN_NUMBER, MAX_NUMBER - 1).map(i -> MAX_NUMBER - i));
        }
    }

    private void iterate(IntStream range) {
        range.forEach(value -> {
                    synchronized (this) {
                        String currentThread = Thread.currentThread().getName();
                        try {
                            while (currentThread.equals(previousThread)) {
                                this.wait();
                            }
                            log.info("{} - {}", currentThread, value);
                            previousThread = currentThread;
                            sleep(500);
                            notifyAll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
